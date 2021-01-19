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
import java.util.ArrayList;
import java.util.List;

public class Tail extends Application {

    public Tail(List<String> args, String inputFile, String outputFile, OutputStreamWriter writer) {
        super(args, inputFile, outputFile, writer);
    }

    @Override
    public String exec() throws IOException {
        if (args.isEmpty()) {
            throw new RuntimeException("tail: missing arguments");
        }
        if (args.size() != 1 && args.size() != 3) {
            throw new RuntimeException("tail: wrong arguments");
        }
        if (args.size() == 3 && !args.get(0).equals("-n")) {
            throw new RuntimeException("tail: wrong argument " + args.get(0));
        }
        int tailLines = 10;
        String tailArg;
        if (args.size() == 3) {
            try {
                tailLines = Integer.parseInt(args.get(1));
            } catch (Exception e) {
                throw new RuntimeException("tail: wrong argument " + args.get(1));
            }
            tailArg = args.get(2);
        } else {
            tailArg = args.get(0);
        }
        File tailFile = new File(Jsh.currentDirectory + File.separator + tailArg);
        if (tailFile.exists()) {
            Charset encoding = StandardCharsets.UTF_8;
            Path filePath = Paths.get((String) Jsh.currentDirectory + File.separator + tailArg);
            ArrayList<String> storage = new ArrayList<>();
            try (BufferedReader reader = Files.newBufferedReader(filePath, encoding)) {
                String line = null;
                while ((line = reader.readLine()) != null) {
                    storage.add(line);
                }
                int index;
                if (tailLines > storage.size()) {
                    index = 0;
                } else {
                    index = storage.size() - tailLines;
                }
                for (int i = index; i < storage.size(); i++) {
                    writer.write(storage.get(i) + System.getProperty("line.separator"));
                    writer.flush();
                }
            } catch (IOException e) {
                throw new RuntimeException("tail: cannot open " + tailArg);
            }
        } else {
            throw new RuntimeException("tail: " + tailArg + " does not exist");
        }
        return "";
    }
}
