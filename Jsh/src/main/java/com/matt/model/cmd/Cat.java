package com.matt.model.cmd;


import com.matt.Jsh;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Cat extends Application {
    public Cat(List<String> args, String inputFile, String outputFile, OutputStreamWriter writer) {
        super(args, inputFile, outputFile, writer);
    }

    @Override
    public String exec() throws IOException {
        if (args.isEmpty()) {
            throw new RuntimeException("cat: missing arguments");
        } else {
            for (String arg : args) {
                Charset encoding = StandardCharsets.UTF_8;
                File currFile = new File(Jsh.currentDirectory + File.separator + arg);
                if (currFile.exists()) {
                    Path filePath = Paths.get(Jsh.currentDirectory + File.separator + arg);
                    try (BufferedReader reader = Files.newBufferedReader(filePath, encoding)) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            writer.write(line);
                            writer.write(System.getProperty("line.separator"));
                            writer.flush();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException("cat: cannot open " + arg);
                    }
                } else {
                    throw new RuntimeException("cat: file does not exist");
                }
            }
        }
        return "";
    }
}
