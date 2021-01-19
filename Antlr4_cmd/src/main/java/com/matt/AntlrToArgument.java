package com.matt;

import com.matt.model.Argument;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

public class AntlrToArgument extends JshGrammarBaseVisitor<Argument> {
    @Override
    public Argument visitArgument(JshGrammarParser.ArgumentContext ctx) {
        List<String> args = new ArrayList<>();
        AntlrToQuoted antlrToQuoted = new AntlrToQuoted();
        for (ParseTree o : ctx.children) {
            if (o instanceof TerminalNode) {
                TerminalNode tnode = (TerminalNode) o;
                Token symbol = tnode.getSymbol();
                if (symbol.getType() == 8) {
                    args.add(symbol.getText());
                }
            } else if (o instanceof JshGrammarParser.QuotedContext) {
                args.add(antlrToQuoted.visit(o).getQuoted());
            }
        }
        return new Argument(args);
    }
}
