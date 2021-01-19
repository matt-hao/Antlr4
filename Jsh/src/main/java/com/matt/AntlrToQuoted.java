package com.matt;

import com.matt.model.Program;
import com.matt.model.Quoted;
import com.matt.util.Antlr4Util;
import org.antlr.v4.runtime.tree.ParseTree;

public class AntlrToQuoted extends JshGrammarBaseVisitor<Quoted> {
    @Override
    public Quoted visitQuoted(JshGrammarParser.QuotedContext ctx) {
        Quoted quoted;
        if (ctx.SINGLEQUOTED() != null) {
            String singleQuoted = ctx.SINGLEQUOTED().getText();
            if (singleQuoted.contains("`")) {
                int start = singleQuoted.indexOf("`");
                int end = singleQuoted.lastIndexOf("`");

                String left = singleQuoted.substring(0, start);
                String right = singleQuoted.substring(end + 1);
                String mid = singleQuoted.substring(start + 1, end);

                ParseTree tree = Antlr4Util.buildParserTree(mid);
                AntlrToProgram antlrToProgram = new AntlrToProgram();
                Program program = antlrToProgram.visit(tree);
                quoted = new Quoted("'" + left + program.toString() + right + "'");
            } else {
                quoted = new Quoted(singleQuoted);
            }
        } else if (ctx.DOUBLEQUOTED() != null) {
            String douboleQuoted = ctx.DOUBLEQUOTED().getText();
            if (douboleQuoted.contains("`")) {
                int start = douboleQuoted.indexOf("`");
                int end = douboleQuoted.lastIndexOf("`");

                String left = douboleQuoted.substring(0, start);
                String right = douboleQuoted.substring(end + 1);
                String mid = douboleQuoted.substring(start + 1, end);

                ParseTree tree = Antlr4Util.buildParserTree(mid);
                AntlrToProgram antlrToProgram = new AntlrToProgram();
                Program program = antlrToProgram.visit(tree);
                quoted = new Quoted("'" + left + program.toString() + right + "'");
            } else {
                quoted = new Quoted(douboleQuoted);
            }
        } else {
            //backQuoted
            ParseTree tree = Antlr4Util.buildParserTree(ctx.BACKQUOTED().getText());
            AntlrToProgram antlrToProgram = new AntlrToProgram();
            Program program = antlrToProgram.visit(tree);
            quoted = new Quoted(program.toString());
        }
        return quoted;
    }
}
