package com.matt.model;

public class PipeCall extends Command implements Producer {
    private Command pipe;
    private Call call;

    public PipeCall(Command pipe, Call call) {
        this.pipe = pipe;
        this.call = call;
    }

    @Override
    public String produce() {
        return call.produceWithInput(pipe.produce());
    }
}