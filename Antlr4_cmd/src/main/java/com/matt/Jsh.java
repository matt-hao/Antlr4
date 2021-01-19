package com.matt;

import com.matt.model.Program;
import com.matt.util.Antlr4Util;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Jsh {

    public static String currentDirectory = System.getProperty("user.dir");

    public static void eval(String cmdline) throws IOException {
        if (cmdline == null || cmdline.trim().length() == 0)
            return;

        ParseTree tree = Antlr4Util.buildParserTree(cmdline);
        AntlrToProgram antlr = new AntlrToProgram();
        Program program = antlr.visit(tree);
        OutputStreamWriter writer =new OutputStreamWriter(System.out);
        writer.write(program.produce());
        writer.flush();
        writer.close();
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            if (args.length != 2) {
                System.out.println("jsh: wrong number of arguments");
                return;
            }
            if (!args[0].equals("-c")) {
                System.out.println("jsh: " + args[0] + ": unexpected argument");
            }
            try {
                eval(args[1]);
            } catch (Exception e) {
                System.out.println("jsh: " + e.getMessage());
            }
        } else {
            Scanner input = new Scanner(System.in);
            try {
                while (true) {
                    String prompt = currentDirectory + "> ";
                    System.out.print(prompt);
                    try {
                        String cmdline = input.nextLine();
                        eval(cmdline);
                    } catch (Exception e) {
                        System.out.println("jsh: " + e.getMessage());
                    }
                }
            } finally {
                input.close();
            }
        }
    }

}
