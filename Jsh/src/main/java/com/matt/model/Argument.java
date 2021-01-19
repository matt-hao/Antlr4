package com.matt.model;

import java.util.List;

public class Argument {
    List<String> args;

    public Argument(List<String> args) {
        this.args = args;
    }

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }
}
