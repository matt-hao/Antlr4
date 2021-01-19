package com.matt.model.cmd;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public abstract class Application {

    protected List<String> args;
    protected String inputFile;
    protected String outPutFile;
    protected OutputStreamWriter writer;

    public Application(List<String> args, String inputFile, String outPutFile, OutputStreamWriter writer) {
        this.args = args;
        this.inputFile = inputFile;
        this.outPutFile = outPutFile;
        this.writer = writer;
    }

    public abstract String exec() throws IOException;
}
