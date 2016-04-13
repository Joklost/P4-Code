package com.company.AST.Visitor;

import com.company.AST.Nodes.*;

import static com.company.AST.SymbolTable.SymbolTable.retrieveSymbol;
import static com.company.AST.Visitor.Types.*;

/**
 * Created by joklost on 12-04-16.
 */
public class SemanticsVisitor extends Visitor implements VisitorInterface {

    private void errorNoDeclaration(String var) {
        System.err.println(var + " has not been declared.");
    }
    private void error(String s) {
        System.err.println(s);
    }

    private boolean assignable(int target, int expression) {
        return true;    // skal laves
    }


    public void defaultVisit(Object o) {

    }

    public void visit(Start s) {

    }

    public void visit(DclBlock db) {

    }

    public void visit(SimBlock s) {

    }

    public void visit(SimStep s) {

    }

    public void visit(Simulation s) {

    }

    public void visit(Interrupts is) {

    }

    public void visit(FunctionDcl fd) {

    }

    public void visit(Param p) {

    }

    public void visit(Program p) {

    }

    public void visit(Dcl ds) {

    }

    public void visit(Assignment as) {
        LHSSemanticsVisitor lhsSemanticsVisitor = new LHSSemanticsVisitor();
        as.targetName.accept(lhsSemanticsVisitor);
        as.expression.accept(this);

        if (assignable(as.targetName.type, as.expression.type)) {
            as.type = as.targetName.type;
        } else {
            error("Right hand side expression not assignable to left hand side name at line " + as.getLineNumber());
            as.type = errorType;
        }

    }


    public void visit(WhileStmt ws) {

    }

    public void visit(ForeachStmt fes) {

    }

    public void visit(ForStmt fs) {

    }

    public void visit(IfStmt i) {

    }

    public void visit(ElseIfStmt e) {

    }

    public void visit(ElseStmt e) {

    }

    public void visit(EndIfStmt e) {

    }

    public void visit(SwitchStmt ss) {

    }

    public void visit(SwitchCase sc) {

    }

    public void visit(SwitchDef sd) {

    }

    public void visit(ReturnExpr r) {

    }

    public void visit(Return r) {

    }

    public void visit(FunctionCallStmt fcs) {

    }

    public void visit(PlusExpr pe) {

    }

    public void visit(MinusExpr me) {

    }

    public void visit(MultExpr me) {

    }

    public void visit(DivExpr de) {

    }

    public void visit(ModExpr me) {

    }

    public void visit(AndExpr ae) {

    }

    public void visit(OrExpr oe) {

    }

    public void visit(LogicEqualsExpr le) {

    }

    public void visit(LessThanExpr le) {

    }

    public void visit(GreaterThanExpr ge) {

    }

    public void visit(LessThanEqualsExpr le) {

    }

    public void visit(GreaterThanEqualsExpr ge) {

    }

    public void visit(NotExpr ne) {

    }

    public void visit(UnMinusExpr ue) {

    }

    public void visit(PlusPlusExpr pe) {

    }

    public void visit(MinusMinusExpr me) {

    }

    public void visit(FunctionCallExpr fe) {

    }

    public void visit(ObjectIdExpr ne) {

    }

    public void visit(StdLiteralExpr se) {

    }

    public void visit(CoordExpr ce) {

    }

    public void visit(EqualsOp eo) {

    }

    public void visit(PlusEqualsOp po) {

    }

    public void visit(MinusEqualsOp mo) {

    }

    public void visit(ModEqualsOp mo) {

    }

    public void visit(MultEqualsOp mo) {

    }

    public void visit(DivEqualsOp deo) {

    }

    public void visit(FunctionCall f) {

    }

    public void visit(ToIterator ti) {

    }

    public void visit(DownToIterator di) {

    }

    public void visit(VariableObjectId vi) {

    }

    public void visit(VariableStdLiteral vs) {

    }

    public void visit(DecimalLiteral dl) {

    }

    public void visit(StringLiteral sl) {

    }

    public void visit(BooleanLiteral bl) {

    }

    public void visit(IntegerLiteral il) {

    }

    public void visit(NullLiteral nl) {

    }

    public void visit(Array1D a) {

    }

    public void visit(Array2D a) {

    }

    public void visit(ListOf l) {

    }

    public void visit(Decimal d) {

    }

    public void visit(StringT s) {

    }

    public void visit(BooleanT b) {

    }

    public void visit(Group g) {

    }

    public void visit(Platoon p) {

    }

    public void visit(Force f) {

    }

    public void visit(Coord c) {

    }

    public void visit(Soldier s) {

    }

    public void visit(Barrier b) {

    }

    public void visit(VectorT v) {

    }

    public void visit(IntegerT i) {

    }

    public void visit(VoidT v) {

    }

    public void visit(Terrain t) {

    }

    public void visit(ObjectReferencing o) {
        o.objectName.accept(this);
        if (o.objectName.type == errorType) {
            o.type = errorType;
        } else {
            if (o.objectName.type != objectTypeDescriptor) {
                error(o.objectName.name + " does not specify an object.");
                o.type = errorType;
            } else {
                ASTNode def = retrieveSymbol(o.fieldName.name);
                if (def == null) {
                    error(o.fieldName.name + " is not a field of " + o.objectName.name);
                    o.type = errorType;
                } else {
                    o.type = def.type;
                }
            }
        }
    }

    public void visit(Array1DReferencing a) {
        a.arrayName.accept(this);
        a.indexExpr.accept(this);

        if (a.arrayName.type == errorType) {
            a.type = errorType;
        } else {
            if (a.arrayName.type != arrayTypeDescriptor) {
                error(a.arrayName.name + " is not an array."); // [] måske
                a.type = errorType;
            } else {
                a.type = a.arrayName.type; // denne type er bestemt at typen af elementer i arrayet
            }
        }
        if (a.indexExpr.type != errorType && a.indexExpr.type != integerType) {
            error("Index expression is not an integer. ArrayName: " + a.arrayName.name);
        }
    }

    public void visit(Array2DReferencing a) {
        a.arrayName.accept(this);
        a.firstIndexExpr.accept(this);
        a.secondIndexExpr.accept(this);

        if (a.arrayName.type == errorType) {
            a.type = errorType;
        } else {
            if (a.arrayName.type != arrayTypeDescriptor) {
                error(a.arrayName.name + " is not an array."); // [] måske
                a.type = errorType;
            } else {
                a.type = a.arrayName.type; // denne type er bestemt at typen af elementer i arrayet
            }
        }
        if (a.firstIndexExpr.type != errorType && a.firstIndexExpr.type != integerType) {
            error("First index expression is not an integer. ArrayName: " + a.arrayName.name);
        }

        if (a.secondIndexExpr.type != errorType && a.secondIndexExpr.type != integerType) {
            error("Second index expression is not an integer. ArrayName: " + a.arrayName.name);
        }
    }


    // tror denne er som den skal være
    public void visit(Identifier id) {
        id.type = errorType;
        id.def = null;
        ASTNode newDef = retrieveSymbol(id.name);
        if (newDef == null) {
            errorNoDeclaration(id.name);
        } else {
            id.def = newDef;
            id.type = newDef.type;
        }
    }

}