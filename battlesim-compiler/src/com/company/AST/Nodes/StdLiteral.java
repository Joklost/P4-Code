package com.company.AST.Nodes;

/**
 * Created by joklost on 01-04-16.
 */
public abstract class StdLiteral extends ASTNode {
    public Object value;

    public StdLiteral(int ln) {
        super(ln);
    }
}
