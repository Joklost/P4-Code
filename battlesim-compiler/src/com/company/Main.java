package com.company;

import com.company.AST.Nodes.Start;
import com.company.CodeGeneration.GenerateJavaVisitor;
import com.company.ContextualAnalysis.SemanticsVisitor;
import com.company.SyntaxAnalysis.Parser;
import com.company.SyntaxAnalysis.Preprocessor;
import com.company.SyntaxAnalysis.Scanner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static boolean errorFound = false;
    public static String currentFile;

    public static void main(String[] args) {

        List<String> paths = new ArrayList<>();

        boolean printCode = false;
        boolean generatedCode = true;
        String outputName = "Main";


        int readArgs = 0;
        if (args.length > 0) {
            for (String s : args) {
                switch (s) {
                    case "-pc":
                        printCode = true;
                        break;
                    case "-o":
                        outputName = args[1 + readArgs];

                        break;
                    default:
                        if (args[readArgs].contains(".bs")) {
                            File f = new File(System.getProperty("user.dir") + File.separator + args[readArgs]);
                            if (f.isFile()) {
                                paths.add(f.getAbsolutePath());
                            }
                        }
                        break;
                }
                readArgs++;
            }
        } else {
            // stier skal ind her, hvis det skal køres fra IntelliJ
            paths.add("/home/joklost/git/P4-Code/battlesim-compiler/battlesim/jonastest/javatest.bs");
        }

        Preprocessor preprocessor = null;
        Scanner scanner;
        Parser parser;
        Start startNode;

        SemanticsVisitor semanticsVisitor = new SemanticsVisitor();
        GenerateJavaVisitor generateJavaVisitor = new GenerateJavaVisitor();

        try {
            for (String path : paths) {
                preprocessor = new Preprocessor(path);
                File f = new File(path);
                currentFile = f.getName();

                String newPath = preprocessor.makeFile();
                scanner = new Scanner(new java.io.FileReader(newPath));
                parser = new Parser(scanner, true);

                startNode = (Start)parser.parse().value;

                if (!parser.errorFound) {
                    startNode.accept(semanticsVisitor);
                    if (!errorFound) {
                        startNode.accept(generateJavaVisitor);
                        Map<String, List<String>> map = generateJavaVisitor.getCode();
                        if (printCode) {
                            for (String s : map.keySet()) {
                                List<String> ls = map.get(s);
                                for (String ss : ls) {
                                    System.out.print(ss);
                                }
                            }
                        }

                        if (generatedCode) {
                            CompileJava cj = new CompileJava(outputName, generateJavaVisitor.getCode());
                            cj.compile();
                        }
                    }
                }



                preprocessor.removeOutFile();




                //SymbolTable.printTable();
            }


        } catch (Exception e) {
            if (preprocessor != null) {
                preprocessor.removeOutFile();
            }
            e.printStackTrace();
        }
    }
}
