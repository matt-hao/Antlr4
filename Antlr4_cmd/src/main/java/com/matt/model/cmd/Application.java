package com.matt.model.cmd;

import java.io.IOException;
import java.util.List;

public abstract class Application {

    protected List<String> args;
    protected String inputFile;
    protected String outPutFile;

    public Application(List<String> args, String inputFile, String outPutFile) {
        this.args = args;
        this.inputFile = inputFile;
        this.outPutFile = outPutFile;
    }

    public abstract String exec() throws IOException;
}
