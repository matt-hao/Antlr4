package com.matt.model.cmd;


import com.matt.Jsh;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Cd extends Application {
    public Cd(List<String> args, String inputFile, String outputFile) {
        super(args, inputFile, outputFile);
    }

    @Override
    public String exec() throws IOException {
        if (args.isEmpty()) {
            throw new RuntimeException("cd: missing argument");
        } else if (args.size() > 1) {
            throw new RuntimeException("cd: too many arguments");
        }
        String dirString = args.get(0);
        File dir = new File(Jsh.currentDirectory, dirString);
        if (!dir.exists() || !dir.isDirectory()) {
            throw new RuntimeException("cd: " + dirString + " is not an existing directory");
        }
        Jsh.currentDirectory = dir.getCanonicalPath();
        return "";
    }
}
