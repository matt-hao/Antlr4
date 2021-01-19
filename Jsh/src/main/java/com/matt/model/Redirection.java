package com.matt.model;

public class Redirection {
    // '<' or '>'
    private String io;
    private String file;

    public Redirection(String io, String file) {
        this.io = io;
        this.file = file;
    }

    public String getIo() {
        return io;
    }

    public String getFile() {
        return file;
    }
}
