package com.matt.model;

public class Program implements Producer {
    private Command command;

    public Program(Command command) {
        this.command = command;
    }


    @Override
    public String produce() {
        return this.command.produce();
    }
}
