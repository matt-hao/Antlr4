package com.matt.model;

public abstract class Command implements Producer {
    @Override
    public abstract String produce();
}
