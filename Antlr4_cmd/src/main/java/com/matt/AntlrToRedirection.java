package com.matt;


import com.matt.model.Argument;
import com.matt.model.Redirection;

public class AntlrToRedirection extends JshGrammarBaseVisitor<Redirection> {
    @Override
    public Redirection visitRedirection(JshGrammarParser.RedirectionContext ctx) {
        String io = ctx.getChild(0).getText();
        AntlrToArgument antlrToArgument = new AntlrToArgument();
        Argument argument = antlrToArgument.visit(ctx.argument());
        // get last string of args
        return new Redirection(io, argument.getArgs().get(argument.getArgs().size() - 1));
    }
}
