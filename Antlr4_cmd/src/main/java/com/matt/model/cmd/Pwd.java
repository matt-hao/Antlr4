package com.matt.model.cmd;


import com.matt.Jsh;

import java.io.IOException;
import java.util.List;

public class Pwd extends Application {
    public Pwd(List<String> args, String inputFile, String outputFile) {
        super(args, inputFile, outputFile);
    }

    @Override
    public String exec() throws IOException {
        return Jsh.currentDirectory + System.getProperty("line.separator");
    }
}
