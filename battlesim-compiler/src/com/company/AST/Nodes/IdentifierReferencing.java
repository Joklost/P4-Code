package com.company.AST.Nodes;

/**
 * Created by joklost on 13-04-16.
 */
public abstract class IdentifierReferencing extends ASTNode {
    public String name;
    public IdentifierReferencing(int ln) {
        super(ln);
    }
}