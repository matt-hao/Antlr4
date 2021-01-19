package com.matt.model.cmd;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class Echo extends Application {
    public Echo(List<String> args, String inputFile, String outputFile, OutputStreamWriter writer) {
        super(args, inputFile, outputFile, writer);
    }

    @Override
    public String exec() throws IOException {
        boolean atLeastOnePrinted = false;
        for (String arg : args) {
            writer.write(arg);
            writer.write(" ");
            writer.flush();
            atLeastOnePrinted = true;
        }
        if (atLeastOnePrinted) {
            writer.write(System.getProperty("line.separator"));
            writer.flush();
        }
        return "";
    }
}
