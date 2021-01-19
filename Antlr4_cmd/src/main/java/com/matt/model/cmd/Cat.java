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

public class Cat extends Application {
    public Cat(List<String> args, String inputFile, String outputFile) {
        super(args, inputFile, outputFile);
    }

    @Override
    public String exec() throws IOException {
        if (args.isEmpty()) {
            throw new RuntimeException("cat: missing arguments");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            for (String arg : args) {
                Charset encoding = StandardCharsets.UTF_8;
                File currFile = new File(Jsh.currentDirectory + File.separator + arg);
                if (currFile.exists()) {
                    Path filePath = Paths.get(Jsh.currentDirectory + File.separator + arg);
                    try (BufferedReader reader = Files.newBufferedReader(filePath, encoding)) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            stringBuilder.append(line).append(System.getProperty("line.separator"));
                        }
                    } catch (IOException e) {
                        throw new RuntimeException("cat: cannot open " + arg);
                    }
                } else {
                    throw new RuntimeException("cat: file does not exist");
                }
            }
            return stringBuilder.toString();
        }
    }
}
