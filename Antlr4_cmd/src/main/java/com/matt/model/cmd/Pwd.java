package com.matt.model.cmd;


import com.matt.Jsh;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class Pwd extends Application {
    public Pwd(List<String> args, String inputFile, String outputFile, OutputStreamWriter writer) {
        super(args, inputFile, outputFile, writer);
    }

    @Override
    public String exec() throws IOException {
        writer.write(Jsh.currentDirectory);
        writer.write(System.getProperty("line.separator"));
        writer.flush();
        return "";
    }
}
