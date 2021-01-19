package com.matt.model.cmd;


import java.io.OutputStreamWriter;
import java.util.List;

public class AppFactory {
    public static Application produceApp(String cmd, List<String> args, String inputFile, String outputFile, OutputStreamWriter writer) {
        switch (cmd) {
            case "cd":
                return new Cd(args, inputFile, outputFile, writer);
            case "pwd":
                return new Pwd(args, inputFile, outputFile, writer);
            case "ls":
                return new Ls(args, inputFile, outputFile, writer);
            case "cat":
                return new Cat(args, inputFile, outputFile, writer);
            case "echo":
                return new Echo(args, inputFile, outputFile, writer);
            case "head":
                return new Head(args, inputFile, outputFile, writer);
            case "tail":
                return new Tail(args, inputFile, outputFile, writer);
            case "grep":
                return new Grep(args, inputFile, outputFile, writer);
            default:
                throw new RuntimeException(cmd + ": unknown application");
        }
    }
}
