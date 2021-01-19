package com.matt;


import com.matt.model.Program;

public class AntlrToProgram extends JshGrammarBaseVisitor<Program> {
    @Override
    public Program visitProg(JshGrammarParser.ProgContext ctx) {
        return new Program(new AntlrToCommand().visitCommand(ctx.command()));
    }
}
