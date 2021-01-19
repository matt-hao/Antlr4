package com.matt.model.cmd;


import java.util.List;

public class AppFactory {
    public static Application produceApp(String cmd, List<String> args, String inputFile, String outputFile) {
        switch (cmd) {
            case "cd":
                return new Cd(args, inputFile, outputFile);
            case "pwd":
                return new Pwd(args, inputFile, outputFile);
            case "ls":
                return new Ls(args, inputFile, outputFile);
            case "cat":
                return new Cat(args, inputFile, outputFile);
            case "echo":
                return new Echo(args, inputFile, outputFile);
            case "head":
                return new Head(args, inputFile, outputFile);
            case "tail":
                return new Tail(args, inputFile, outputFile);
            case "grep":
                return new Grep(args, inputFile, outputFile);
            default:
                throw new RuntimeException(cmd + ": unknown application");
        }
    }
}
