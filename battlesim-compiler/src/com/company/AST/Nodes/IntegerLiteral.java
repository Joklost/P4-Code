package com.company.AST.Nodes;

/**
 * Created by joklost on 01-04-16.
 */
public class IntegerLiteral extends StdLiteral {

    public IntegerLiteral(Integer i, int ln) {
        super(ln);
        this.value = i;
    }
}
