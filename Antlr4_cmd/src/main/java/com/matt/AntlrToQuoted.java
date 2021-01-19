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
                quoted = new Quoted("'" + left + program.produce() + right + "'");
            } else {
                quoted = new Quoted(singleQuoted);
            }
        } else if (ctx.DOUBLEQUOTED() != null) {
            String doubleQuoted = ctx.DOUBLEQUOTED().getText();
            doubleQuoted = doubleQuoted.substring(1, doubleQuoted.length() - 1);
            if (doubleQuoted.contains("`")) {
                int start = doubleQuoted.indexOf("`");
                int end = doubleQuoted.lastIndexOf("`");

                String left = doubleQuoted.substring(0, start);
                String right = doubleQuoted.substring(end + 1);
                String mid = doubleQuoted.substring(start + 1, end);

                ParseTree tree = Antlr4Util.buildParserTree(mid);
                AntlrToProgram antlrToProgram = new AntlrToProgram();
                Program program = antlrToProgram.visit(tree);
                quoted = new Quoted("\"" + left + program.produce() + right + "\"");
            } else {
                quoted = new Quoted(doubleQuoted);
            }
        } else {
            //backQuoted
            String text = ctx.BACKQUOTED().getText();
            ParseTree tree = Antlr4Util.buildParserTree(text.substring(1, text.length() - 1));
            AntlrToProgram antlrToProgram = new AntlrToProgram();
            Program program = antlrToProgram.visit(tree);
            quoted = new Quoted(program.produce());
        }
        return quoted;
    }
}
