package com.company.AST.Visitor;

import com.company.AST.Nodes.*;

/**
 * Created by r5hej on 25-04-16.
 */
public class PrettyPrintVisitor extends Visitor implements VisitorInterface {
    private int indentLevel;

    public PrettyPrintVisitor() {
        indentLevel = 0;
    }

    private void printIndent() {
        for (int i = 0; i < indentLevel; i++) {
            System.out.print("  ");
        }
    }

    private void println(String message) {
        printIndent();
        System.out.println(message);
    }

    private void print(String message) {
        System.out.print(message);
    }

    public void defaultVisit(Object o) {
        println("Error with object: " + o.getClass());
    }


    public void visit(Start s){
        System.out.println("Pretty Printing:");
        System.out.println("----------------\n");

        s.dclBlock.accept(this);
        println("");


        s.simBlock.accept(this);
        println("");

        for (int i = 0; i < s.functionDclList.size(); i++) {
            s.functionDclList.elementAt(i).accept(this);
            println("");
        }

        s.program.accept(this);
        println("");
    }

    public void visit(DclBlock db){
        println("Begin Declarations");
        indentLevel++;

        for (int i = 0; i < db.stmtLists.size(); i++){
            db.stmtLists.elementAt(i).accept(this);
        }

        indentLevel--;
        println("End Declarations");
    }

    public void visit(SimBlock s){
        println("Declare Simulations");
        indentLevel++;

        for (int i = 0; i < s.simulationList.size(); i++){
            s.simulationList.elementAt(i).accept(this);
        }

        indentLevel--;
        println("End Declare Simulations");
    }

    public void visit(SimStep s){
        printIndent();
        print("Step " + s.stepNumber.intValue() + "\n");

        indentLevel++;

        for (int i = 0; i < s.stmtList.size(); i++){
            s.stmtList.elementAt(i).accept(this);
        }
        indentLevel--;
    }

    public void visit(Simulation s){
        printIndent();
        print("Simulation ");
        s.identifier.accept(this);
        print(" ");
        s.objectIdentifier.accept(this);
        print("\n");

        indentLevel++;
        for (int i = 0; i < s.simStepList.size(); i++){
            s.simStepList.elementAt(i).accept(this);
        }

        s.interrupts.accept(this);

        indentLevel--;

        println("End Simulation");
    }

    public void visit(Interrupts is){
        println("Interrupts");

        indentLevel++;
        for (int i = 0; i < is.stmtList.size(); i++){
            is.stmtList.elementAt(i).accept(this);
        }
        indentLevel--;
    }

    @Override
    public void visit(TypeDeclaration t) {

    }

    public void visit(JavaString j) {
        println(j.javaCode);
    }

    public void visit(FunctionDcl fd){
        printIndent();
        print("Function ");
        fd.returnType.accept(this);
        print(" ");
        fd.functionName.accept(this);
        print("(");

        for (int i = 0; i < fd.paramList.size(); i++){
            fd.paramList.elementAt(i).accept(this);
            if (i < fd.paramList.size() -1)
                print(", ");
        }

        print(")\n");
        indentLevel++;

        for (int i = 0; i < fd.stmtList.size(); i++){
            fd.stmtList.elementAt(i).accept(this);
        }

        indentLevel--;
        println("End Function");
    }

    public void visit(Param p){
        p.identifier.accept(this);
        print(" as ");
        p.typeIdentifier.accept(this);
    }

    public void visit(Program p){
        println("Begin Program");
        indentLevel++;

        for (int i = 0; i < p.stmtList.size(); i++){
            p.stmtList.elementAt(i).accept(this);
        }

        indentLevel--;
        println("End Program");
    }

    public void visit(Dcl ds){
        printIndent();
        print("Declare ");

        for (int i = 0; i < ds.dclIdList.size(); i++){
            ds.dclIdList.elementAt(i).accept(this);
            if (i < ds.dclIdList.size() - 1)
                print(", ");

        }

        print(" as ");
        ds.typeName.accept(this);
        print("\n");
    }

    public void visit(Assignment as){
        printIndent();
        as.targetName.accept(this);
        as.assignOp.accept(this);
        as.expression.accept(this);
        print("\n");
    }


    public void visit(WhileStmt ws){
        printIndent();
        print("While ");
        ws.condition.accept(this);
        print(" Do\n");

        indentLevel++;
        for (int i = 0; i < ws.stmtList.size(); i++){
            ws.stmtList.elementAt(i).accept(this);
        }

        indentLevel--;
        println("End While");
    }

    public void visit(ForeachStmt fes){
        printIndent();
        print("Foreach ");
        fes.typeName.accept(this);
        print(" ");
        fes.localName.accept(this);
        print(" in ");
        fes.objectName.accept(this);
        print(" Do\n");

        indentLevel++;
        for (int i = 0; i < fes.stmtList.size(); i++){
            fes.stmtList.elementAt(i).accept(this);
        }

        indentLevel--;
        println("End Foreach");
    }

    public void visit(ForStmt fs){
        printIndent();
        print("For ");
        fs.var.accept(this);
        fs.forIterator.accept(this);
        fs.toExpr.accept(this);
        print(" Do\n");

        indentLevel++;
        for (int i = 0; i < fs.stmtList.size(); i++){
            fs.stmtList.elementAt(i).accept(this);
        }

        indentLevel--;
        println("End For");
    }

    public void visit(IfStmt i){
        printIndent();
        print("If ");
        i.condition.accept(this);
        print(" Then\n");

        indentLevel++;
        for (int k = 0; k < i.stmtList.size(); k++){
            i.stmtList.elementAt(k).accept(this);
        }
        indentLevel--;
        i.elseStmt.accept(this);
    }

    public void visit(ElseIfStmt e){
        printIndent();
        print("Else If ");
        e.condition.accept(this);
        print(" Then\n");

        indentLevel++;
        for (int i = 0; i < e.stmtList.size(); i++){
            e.stmtList.elementAt(i).accept(this);
        }
        indentLevel--;
        e.elifStmt.accept(this);
    }

    public void visit(ElseStmt e){
        printIndent();
        println("Else");

        indentLevel++;
        for (int i = 0; i < e.stmtList.size(); i++){
            e.stmtList.elementAt(i).accept(this);
        }

        indentLevel--;
        println("End If");
    }

    public void visit(EndIfStmt e){
        println("End If");
    }

    public void visit(SwitchStmt ss){
        printIndent();
        print("Switch ");
        ss.control.accept(this);
        print("\n");

        indentLevel++;
        for (int i = 0; i < ss.switchCaseList.size(); i++){
            ss.switchCaseList.elementAt(i).accept(this);
        }

        if (ss.switchDef != null)
            ss.switchDef.accept(this);

        indentLevel--;
        println("End Switch");
    }

    public void visit(SwitchCase sc){
        print("Case ");
        sc.label.accept(this);
        print("\n");

        indentLevel++;
        for (int i = 0; i < sc.stmtList.size(); i++){
            sc.stmtList.elementAt(i).accept(this);
        }

        indentLevel--;
    }

    public void visit(SwitchDef sd){
        println("Default");

        indentLevel++;
        for (int i = 0; i < sd.stmtList.size(); i++){
            sd.stmtList.elementAt(i).accept(this);
        }
        indentLevel--;
    }


    public void visit(ReturnExpr r){
        printIndent();
        print("Return ");
        r.returnVal.accept(this);
        print("\n");
    }

    public void visit(Return r){
        print("Return ");
    }

    public void visit(FunctionCallStmt fcs){
        printIndent();
        fcs.functionCall.accept(this);
        print("\n");
    }


    public void visit(PlusPlusStmt s){
        s.id.accept(this);
        print("++\n");
    }

    public void visit(MinusMinusStmt s){
        s.id.accept(this);
        print("--\n ");
    }


    public void visit(PlusExpr pe){
        print("(") ;
        pe.leftExpr.accept(this);
        print(" + ");
        pe.rightExpr.accept(this);
        print(")");
    }

    public void visit(MinusExpr me){
        print("(");
        me.leftExpr.accept(this);
        print(" - ");
        me.rightExpr.accept(this);
        print(")");
    }

    public void visit(MultExpr me){
        print("(");
        me.leftExpr.accept(this);
        print(" * ");
        me.rightExpr.accept(this);
        print(")");
    }

    public void visit(DivExpr de){
        print("(");
        de.leftExpr.accept(this);
        print(" / ");
        de.rightExpr.accept(this);
        print(")");
    }

    public void visit(ModExpr me){
        print("(");
        me.leftExpr.accept(this);
        print(" % ");
        me.rightExpr.accept(this);
        print(")");
    }

    public void visit(AndExpr ae){
        print("(");
        ae.leftExpr.accept(this);
        print(" AND ");
        ae.rightExpr.accept(this);
        print(")");
    }

    public void visit(OrExpr oe){
        print("(");
        oe.leftExpr.accept(this);
        print(" OR ");
        oe.rightExpr.accept(this);
        print(")");
    }

    public void visit(LogicEqualsExpr le){
        print("(");
        le.leftExpr.accept(this);
        print(" EQUALS ");
        le.rightExpr.accept(this);
        print(")");
    }

    public void visit(LessThanExpr le){
        print("(");
        le.leftExpr.accept(this);
        print(" < ");
        le.rightExpr.accept(this);
        print(")");
    }

    public void visit(GreaterThanExpr ge){
        print("(");
        ge.leftExpr.accept(this);
        print(" > ");
        ge.rightExpr.accept(this);
        print(")");
    }

    public void visit(LessThanEqualsExpr le){
        print("(");
        le.leftExpr.accept(this);
        print(" <= ");
        le.rightExpr.accept(this);
        print(")");
    }

    public void visit(GreaterThanEqualsExpr ge){
        print("(");
        ge.leftExpr.accept(this);
        print(" >= ");
        ge.rightExpr.accept(this);
        print(")");
    }

    public void visit(NotExpr ne){
        print("(");
        print("NOT ");
        ne.expression.accept(this);
        print(")");
    }

    public void visit(UnMinusExpr ue){
        print("-");
        ue.expression.accept(this);
    }

    public void visit(PlusPlusExpr pe){
        pe.expression.accept(this);
        print("++");
    }

    public void visit(MinusMinusExpr me){
        me.expression.accept(this);
        print("--");
    }

    public void visit(FunctionCallExpr fe){
        //printIndent();
        fe.functionCall.accept(this);
    }

    public void visit(ObjectIdExpr ne){
        //printIndent();
        ne.objectName.accept(this);
    }

    public void visit(StdLiteralExpr se){
        se.stdLiteral.accept(this);
    }

    public void visit(CoordExpr ce){
        printIndent();
        print("coord = (");
        ce.expression1.accept(this);
        print(", ");
        ce.expression2.accept(this);
        print(")\n");
    }

    public void visit(EqualsOp eo){
        print(" = ");
    }

    public void visit(PlusEqualsOp po){
        print(" += ");
    }

    public void visit(MinusEqualsOp mo){
        print(" -= ");
    }

    public void visit(ModEqualsOp mo){
        print(" %= ");
    }

    public void visit(MultEqualsOp mo){
        print(" *= ");
    }

    public void visit(DivEqualsOp deo){
        print(" /= ");
    }

    public void visit(FunctionCall f){
        //printIndent();
        f.objectName.accept(this);
        print(" (");

        for (int i = 0; i < f.argumentList.size(); i++){
            f.argumentList.elementAt(i).accept(this);
            if (i < f.argumentList.size() - 1)
                print(", ");
        }
        print(")");
    }

    public void visit(ToIterator ti){
        print(" To ");
    }

    public void visit(DownToIterator di){
        print(" DownTo ");
    }

    public void visit(VariableObjectId vi){
        print(vi.objectName.toString());
    }

    public void visit(VariableStdLiteral vs){
        print(vs.stdLiteral.toString());
    }

    public void visit(DecimalLiteral dl){
        print(dl.value.toString());
    }

    public void visit(StringLiteral sl){
        print("\"" + sl.string + "\"");
    }

    public void visit(BooleanLiteral bl){ print(bl.value.toString()); }

    public void visit(IntegerLiteral il){
        print(il.value.toString());
    }

    public void visit(NullLiteral nl){
        print("NULL");
    }

    public void visit(Array1D a){
        a.typeName.accept(this);
        print("[");
        a.size.accept(this);
        print("]");
    }

    public void visit(Array2D a){
        printIndent();
        a.typeName.accept(this);
        print("[");
        a.size1.accept(this);
        print("][");
        a.size2.accept(this);
        print("]");
    }

    public void visit(ListOf l){
        print("List Of ");
        l.typeName.accept(this);
        print("\n");
    }

    public void visit(Decimal d){
        print("Decimal");
    }

    public void visit(StringT s){
        print("String");
    }

    public void visit(BooleanT b){
        print("Boolean");
    }

    public void visit(Group g){
        print("Group");
    }

    public void visit(Platoon p){
        print("Platoon");
    }

    public void visit(Force f){
        print("Force");
    }

    public void visit(Coord c){
        print("Coord");
    }

    public void visit(Soldier s){
        print("Soldier");
    }

    public void visit(Barrier b){
        print("Barrier");
    }

    public void visit(VectorT v) {
        print("Vector");
    }

    public void visit(IntegerT i){
        print("Integer");
    }

    public void visit(VoidT v){
        print("Void");
    }

    @Override
    public void visit(CustomTypeIdentifier o) {

    }

    public void visit(Terrain t){
        print("Terrain");
    }

    public void visit(ObjectReferencing o){
        o.objectName.accept(this);
        print(".");
        o.fieldName.accept(this);
    }

    public void visit(Array1DReferencing a){
        a.arrayName.accept(this);
        print("[");
        a.indexExpr.accept(this);
        print("]");
    }

    public void visit(Array2DReferencing a){
        a.arrayName.accept(this);
        print("[");
        a.firstIndexExpr.accept(this);
        print("][");
        a.secondIndexExpr.accept(this);
        print("]");
    }

    public void visit(Identifier id){
        print(id.name);
    }

    public void visit(JavaStringStmt j){
        print(j.javaCode);
    }
}
