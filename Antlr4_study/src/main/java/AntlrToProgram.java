import model.Program;

import java.util.ArrayList;
import java.util.List;

public class AntlrToProgram extends ExprBaseVisitor<Program> {

    private List<String> semanticErrors;

    @Override
    public Program visitProgram(ExprParser.ProgramContext ctx) {
        Program program = new Program();
        semanticErrors = new ArrayList<>();
        AntlrToExpression antlrToExpression = new AntlrToExpression(semanticErrors);
        for (int i = 0; i < ctx.getChildCount() - 1; i++) {
            if (i == ctx.getChildCount() - 1) {
                //last child won't be converted to an model.Expression Object
            } else {
                program.addExpression(antlrToExpression.visit(ctx.getChild(i)));
            }
        }
        return program;
    }

    public List<String> getSemanticErrors() {
        return semanticErrors;
    }
}
