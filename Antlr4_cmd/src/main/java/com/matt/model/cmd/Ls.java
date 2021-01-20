package com.matt.model.cmd;


import com.matt.Jsh;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Ls extends Application {
    public Ls(List<String> args, String inputFile, String outputFile) {
        super(args, inputFile, outputFile);
    }

    @Override
    public String exec() throws IOException {
        File currDir;
        if (args.isEmpty()) {
            currDir = new File(Jsh.currentDirectory);
        } else if (args.size() == 1) {
            currDir = new File(args.get(0));
        } else {
            throw new RuntimeException("ls: too many arguments");
        }
        File[] listOfFiles = currDir.listFiles();
        if (listOfFiles == null || listOfFiles.length == 0)
            return "";

        StringBuilder stringBuilder = new StringBuilder();
        boolean atLeastOnePrinted = false;
        for (File file : listOfFiles) {
            if (file == null || file.getName().length() == 0) continue;
            if (!file.getName().startsWith(".")) {
                stringBuilder.append(file.getName()).append("\t");
                atLeastOnePrinted = true;
            }
        }
        if (atLeastOnePrinted) {
            stringBuilder.append(System.getProperty("line.separator"));
        }
        return stringBuilder.toString();
    }
}
