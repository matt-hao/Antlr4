package com.matt.model;


import com.matt.model.cmd.AppFactory;
import com.matt.model.cmd.Application;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class Call extends Command implements Producer {
    private Argument argument;
    private String ioInput;
    private String ioOutput;

    public Call(Argument argument, String ioInput, String ioOutput) {
        this.argument = argument;
        this.ioInput = ioInput;
        this.ioOutput = ioOutput;
    }

    public Argument getArgument() {
        return argument;
    }

    @Override
    public String produce() {
        String appName = argument.args.get(0);
        List<String> args = argument.args.subList(1, argument.args.size());
        Application app = AppFactory.produceApp(appName, args, this.ioInput, this.ioOutput, new OutputStreamWriter(System.out));
        try {
            return app.exec();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1); // terminate the program anyway
        }
        throw new RuntimeException("produce method does not work properly.");
    }

    public String produceWithInput(String input) {
        if (input == null || input.trim().equals(""))
            throw new RuntimeException("Input for produceWithInput method should not be empty");
        String appName = argument.args.get(0);
        List<String> args = argument.args.subList(1, argument.args.size());
        Application app = AppFactory.produceApp(appName, args, input, this.ioOutput, new OutputStreamWriter(System.out));
        try {
            return app.exec();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1); // terminate the program anyway
        }
        throw new RuntimeException("produce in Pipeline does not work properly.");
    }
}
