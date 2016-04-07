package com.company.AST;

/**
 * Created by joklost on 01-04-16.
 */
public class Coord2DArray extends TypeIdentifier {
    public Expression expression1;
    public Expression expression2;

    public Coord2DArray(Expression e1, Expression e2, int ln) {
        super(ln);
        this.expression1 = e1;
        this.expression2 = e2;
    }
}
