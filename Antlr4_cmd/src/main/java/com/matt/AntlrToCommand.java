package com.matt;

import com.matt.model.*;
import org.antlr.v4.runtime.tree.ParseTree;

public class AntlrToCommand extends JshGrammarBaseVisitor<Command> {

    @Override
    public Command visitCommand(JshGrammarParser.CommandContext ctx) {
        Command c;
        AntlrToCommand antlrToCommand = new AntlrToCommand();
        if (ctx.call() != null) {
            c = antlrToCommand.visitCall(ctx.call());
        } else if (ctx.pipe() != null) {
            c = antlrToCommand.visitPipe(ctx.pipe());
        } else {
            Command c1 = antlrToCommand.visitCommand(ctx.command(0));
            Command c2 = antlrToCommand.visitCommand(ctx.command(1));
            c = new DoubleCommand(c1, c2);
        }
        return c;
    }

    @Override
    public Command visitPipe(JshGrammarParser.PipeContext ctx) {
        Command p;
        AntlrToCommand antlrToCall = new AntlrToCommand();
        if (ctx.pipe() != null) {
            Command pipe = visit(ctx.pipe());
            Call call = (Call) antlrToCall.visit(ctx.call(0));
            p = new PipeCall(pipe, call);
        } else {
            Call c1 = (Call) antlrToCall.visit(ctx.call(0));
            Call c2 = (Call) antlrToCall.visit(ctx.call(1));
            p = new CallCall(c1, c2);
        }
        return p;
    }

    @Override
    public Command visitCall(JshGrammarParser.CallContext ctx) {
        AntlrToArgument antlrToArgument = new AntlrToArgument();
        String input = null;
        String output = null;
        if (!ctx.redirection().isEmpty()) {
            AntlrToRedirection antlrToRedirection = new AntlrToRedirection();
            for (int i = ctx.redirection().size() - 1; i >= 0; i--) {
                ParseTree o = ctx.redirection(i);
                Redirection r = antlrToRedirection.visit(o);
                if (input == null && r.getIo().equals("<")) {
                    input = r.getFile();
                }
                if (output == null && r.getIo().equals(">")) {
                    output = r.getFile();
                }
                if (input != null && output != null)
                    break;
            }
        }
        return new Call(antlrToArgument.visit(ctx.argument()), input, output);
    }
}
