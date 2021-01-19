package com.matt.model.cmd;

import java.io.IOException;
import java.util.List;

public class Echo extends Application {
    public Echo(List<String> args, String inputFile, String outputFile) {
        super(args, inputFile, outputFile);
    }

    @Override
    public String exec() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        for (String arg : args) {
            stringBuilder.append(arg).append(" ");
        }
        stringBuilder.append(System.getProperty("line.separator"));
        return stringBuilder.toString();
    }
}
