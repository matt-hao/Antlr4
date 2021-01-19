package com.matt.model;


public class CallCall extends Command implements Producer {
    private Call c1;
    private Call c2;

    public CallCall(Call c1, Call c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public String produce() {
        return c2.produceWithInput(c1.produce());
    }
}
