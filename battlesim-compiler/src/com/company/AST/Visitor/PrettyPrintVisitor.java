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
/*
    void visit(Start s)
    {
    }
    void visit(DclBlock db)
    {
    }
    void visit(SimBlock s)
    {
    }
    void visit(SimStep s)
    {
    }
    void visit(Simulation s)
    {
    }
    void visit(Interrupts is)
    {
    }
    void visit(FunctionDcl fd)
    {
    }
    void visit(Param p)
    {
    }
    void visit(Program p)
    {
    }
    void visit(Dcl ds)
    {
    }
    void visit(Assignment as)
    {
    }
    void visit(WhileStmt ws)
    {
    }
    void visit(ForeachStmt fes)
    {
    }
    void visit(ForStmt fs)
    {
    }
    void visit(IfStmt i)
    {
    }
    void visit(ElseIfStmt e)
    {
    }
    void visit(ElseStmt e)
    {
    }
    void visit(EndIfStmt e)
    {
    }
    void visit(SwitchStmt ss)
    {
    }
    void visit(SwitchCase sc)
    {
    }
    void visit(SwitchDef sd)
    {
    }
    void visit(ReturnExpr r)
    {
    }
    void visit(Return r)
    {
    }
    void visit(FunctionCallStmt fcs)
    {
    }
    void visit(PlusPlusStmt s)
    {
    }
    void visit(MinusMinusStmt s)
    {
    }
    void visit(PlusExpr pe)
    {
    }
    void visit(MinusExpr me)
    {
    }
    void visit(MultExpr me)
    {
    }
    void visit(DivExpr de)
    {
    }
    void visit(ModExpr me)
    {
    }
    void visit(AndExpr ae)
    {
    }
    void visit(OrExpr oe)
    {
    }
    void visit(LogicEqualsExpr le)
    {
    }
    void visit(LessThanExpr le)
    {
    }
    void visit(GreaterThanExpr ge)
    {
    }
    void visit(LessThanEqualsExpr le)
    {
    }
    void visit(GreaterThanEqualsExpr ge)
    {
    }
    void visit(NotExpr ne)
    {
    }
    void visit(UnMinusExpr ue)
    {
    }
    void visit(PlusPlusExpr pe)
    {
    }
    void visit(MinusMinusExpr me)
    {
    }
    void visit(FunctionCallExpr fe)
    {
    }
    void visit(ObjectIdExpr ne)
    {
    }
    void visit(StdLiteralExpr se)
    {
    }
    void visit(CoordExpr ce)
    {
    }
    void visit(EqualsOp eo)
    {
    }
    void visit(PlusEqualsOp po)
    {
    }
    void visit(MinusEqualsOp mo)
    {
    }
    void visit(ModEqualsOp mo)
    {
    }
    void visit(MultEqualsOp mo)
    {
    }
    void visit(DivEqualsOp deo)
    {
    }
    void visit(FunctionCall f)
    {
    }
    void visit(ToIterator ti)
    {
    }
    void visit(DownToIterator di)
    {
    }
    void visit(VariableObjectId vi)
    {
    }
    void visit(VariableStdLiteral vs)
    {
    }
    void visit(DecimalLiteral dl)
    {
    }
    void visit(StringLiteral sl)
    {
    }
    void visit(BooleanLiteral bl)
    {
    }
    void visit(IntegerLiteral il)
    {
    }
    void visit(NullLiteral nl)
    {
    }
    void visit(Array1D a)
    {
    }
    void visit(Array2D a)
    {
    }
    void visit(ListOf l)
    {
    }
    void visit(Decimal d)
    {
    }
    void visit(StringT s)
    {
    }
    void visit(BooleanT b)
    {
    }
    void visit(Group g)
    {
    }
    void visit(Platoon p)
    {
    }
    void visit(Force f)
    {
    }
    void visit(Coord c)
    {
    }
    void visit(Soldier s)
    {
    }
    void visit(Barrier b)
    {
    }
    void visit(IntegerT i)
    {
    }
    void visit(VoidT v)
    {
    }
    void visit(Terrain t)
    {
    }
    void visit(ObjectReferencing o)
    {
    }
    void visit(Array1DReferencing a)
    {
    }
    void visit(Array2DReferencing a)
    {
    }
    void visit(Identifier id)
    {
    }
    void visit(JavaString j)
    {
    }
    */
}
