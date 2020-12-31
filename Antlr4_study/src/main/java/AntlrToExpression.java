import model.*;
import model.Number;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;

public class AntlrToExpression extends ExprBaseVisitor<Expression> {
    /**
     * Top-down
     */
    private final List<String> vars;
    private final List<String> semanticErrors; // 1. duplicate declaration 2. reference to undeclared

    public AntlrToExpression(List<String> semanticErrors) {
        this.vars = new ArrayList<>();
        this.semanticErrors = semanticErrors;
    }

    @Override
    public Expression visitDeclaration(ExprParser.DeclarationContext ctx) {
        Token idToken = ctx.ID().getSymbol(); // ctx.getChild(0).getSymbol()
        int row = idToken.getLine();
        int col = idToken.getCharPositionInLine() + 1;

        String id = ctx.getChild(0).getText();
        if (vars.contains(id)) {
            semanticErrors.add("Error: variable " + id + " already declared ( " + row + ", " + col + " )");
        } else {
            vars.add(id);
        }
        return new VariableDeclaration(id, ctx.INT_TYPE().getText(), Integer.parseInt(ctx.NUM().getText()));
    }

    @Override
    public Expression visitMultiplication(ExprParser.MultiplicationContext ctx) {
        //recursively retrieve the tree
        return new Multiplication(visit(ctx.getChild(0)), visit(ctx.getChild(2)));
    }

    @Override
    public Expression visitAddition(ExprParser.AdditionContext ctx) {
        return new Addition(visit(ctx.getChild(0)), visit(ctx.getChild(2)));
    }

    @Override
    public Expression visitVariable(ExprParser.VariableContext ctx) {
        Token idToken = ctx.ID().getSymbol();
        int row = idToken.getLine();
        int col = idToken.getCharPositionInLine() + 1;

        String id = ctx.ID().getText();
        if (!vars.contains(id)) {
            semanticErrors.add("Error: variable " + id + " not declared ( " + row + ", " + col + " )");
        }
        return new Variable(id);
    }

    @Override
    public Expression visitNumber(ExprParser.NumberContext ctx) {
        return new Number(Integer.parseInt(ctx.getChild(0).getText()));
    }
}
