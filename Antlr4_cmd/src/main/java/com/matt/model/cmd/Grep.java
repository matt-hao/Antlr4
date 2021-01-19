package com.matt.model.cmd;


import com.matt.Jsh;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep extends Application {
    public Grep(List<String> args, String inputFile, String outputFile) {
        super(args, inputFile, outputFile);
    }

    @Override
    public String exec() {
        if (args.size() < 2) {
            throw new RuntimeException("grep: wrong number of arguments");
        }
        Pattern grepPattern = Pattern.compile(args.get(0));
        int numOfFiles = args.size() - 1;
        Path filePath;
        Path[] filePathArray = new Path[numOfFiles];
        Path currentDir = Paths.get(Jsh.currentDirectory);
        for (int i = 0; i < numOfFiles; i++) {
            filePath = currentDir.resolve(args.get(i + 1));
            if (Files.notExists(filePath) || Files.isDirectory(filePath) ||
                    !Files.exists(filePath) || !Files.isReadable(filePath)) {
                throw new RuntimeException("grep: wrong file argument");
            }
            filePathArray[i] = filePath;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int j = 0; j < filePathArray.length; j++) {
            Charset encoding = StandardCharsets.UTF_8;
            try (BufferedReader reader = Files.newBufferedReader(filePathArray[j], encoding)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Matcher matcher = grepPattern.matcher(line);
                    if (matcher.find()) {
                        if (numOfFiles > 1) {
                            stringBuilder.append(args.get(j + 1)).append(":");
                        }
                        stringBuilder.append(line).append(System.getProperty("line.separator"));
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException("grep: cannot open " + args.get(j + 1));
            }

        }
        return stringBuilder.toString();
    }
}
