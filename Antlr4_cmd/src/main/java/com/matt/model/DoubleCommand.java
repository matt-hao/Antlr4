package com.matt.model;

public class DoubleCommand extends Command implements Producer {
    private Command c1;
    private Command c2;

    public DoubleCommand(Command c1, Command c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public String produce() {
        return this.c1.produce() + "\n" + this.c2.produce();
    }
}
