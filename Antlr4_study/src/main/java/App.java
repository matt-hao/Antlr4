import model.Program;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.print("Usage: file name");
        } else {
            String fileName = args[0];
            ExprParser parser = getParser(fileName);

            // build a tree
            ParseTree tree = parser.prog();

            if (!ErrorListener.hasError) {
                // Create visitor to convert tree into model.Program/model.Expression Object
                AntlrToProgram antlrToProgram = new AntlrToProgram();
                Program program = antlrToProgram.visit(tree);
                if (antlrToProgram.getSemanticErrors().isEmpty()) {
                    ExpressionProcessor expressionProcessor = new ExpressionProcessor(program.getExpressions());
                    for (String eval : expressionProcessor.getEvaluationResults()) {
                        System.out.println(eval);
                    }
                } else {
                    for (String err : antlrToProgram.getSemanticErrors()) {
                        System.out.println(err);
                    }
                }
            }
        }
    }

    private static ExprParser getParser(String fileName) throws IOException {
        CharStream input = CharStreams.fromFileName(fileName);
        ExprLexer exprLexer = new ExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(exprLexer);
        ExprParser parser = new ExprParser(tokens);

        parser.removeErrorListeners();
        parser.addErrorListener(new ErrorListener());
        return parser;
    }
}
