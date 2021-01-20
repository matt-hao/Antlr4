package com.matt.model.cmd;


import com.matt.Jsh;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Head extends Application {
    public Head(List<String> args, String inputFile, String outputFile) {
        super(args, inputFile, outputFile);
    }

    @Override
    public String exec() {
        if (args.isEmpty()) {
            throw new RuntimeException("head: missing arguments");
        }
        if (!(args.size() == 1 || args.size() == 3)) {
            throw new RuntimeException("head: wrong arguments");
        }
        if (args.size() == 3 ) {
            if(!args.get(0).equals("-n"))
                throw new RuntimeException("head: wrong argument " + args.get(0));
            // todo.... number? negative number? double or float? write a function to rule out wrong case
        }
        int headLines = 10;
        String headArg;
        if (args.size() == 3) {
            try {
                headLines = Integer.parseInt(args.get(1));
            } catch (Exception e) {
                throw new RuntimeException("head: wrong argument " + args.get(1));
            }
            headArg = args.get(2);
        } else {
            headArg = args.get(0);
        }
        File headFile = new File(Jsh.currentDirectory + File.separator + headArg);
        if (headFile.exists()) {
            StringBuilder stringBuilder = new StringBuilder();
            Charset encoding = StandardCharsets.UTF_8;
            Path filePath = Paths.get(Jsh.currentDirectory + File.separator + headArg);
            try (BufferedReader reader = Files.newBufferedReader(filePath, encoding)) {
                for (int i = 0; i < headLines; i++) {
                    String line;
                    if ((line = reader.readLine()) != null) {
                        stringBuilder.append(line).append(System.getProperty("line.separator"));
                    }
                }
                return stringBuilder.toString();
            } catch (IOException e) {
                throw new RuntimeException("head: cannot open " + headArg);
            }
        } else {
            throw new RuntimeException("head: " + headArg + " does not exist");
        }
    }
}
