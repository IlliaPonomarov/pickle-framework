package com.pickle;
import com.pickle.parsers.FileParser;
import com.pickle.services.FileService;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;


public class Pickle {

    private static final Logger loggerMain = LoggerFactory.getLogger(Pickle.class);
    public static void main(String[] args) {
        CommandLine myArgumentParser;
        String inputPath = "C:\\Users\\hp\\IdeaProjects\\pickle-framework\\src\\test\\java\\files\\yaml\\test1.yaml";
        String outputPath = "";
        Optional<File[]> inputFiles;
        FileService fileService = new FileService();

        if (args.length == 0) {
            loggerMain.error("No arguments provided");
            System.exit(1);
        }

        myArgumentParser = MyArgumentParser.build(args);
        inputPath = myArgumentParser.getOptionValue("i");
        loggerMain.info(inputPath);
        System.out.println("What is the input path: " + inputPath);

        if ((myArgumentParser.hasOption("i") || myArgumentParser.hasOption("input")) && (myArgumentParser.hasOption("o") || myArgumentParser.hasOption("output"))) {
            System.out.println("Input");
            inputFiles = Optional.ofNullable(new File(inputPath).listFiles());

            if (inputFiles.isEmpty()) {
                loggerMain.error("No files found in the input directory");
                System.exit(1);
            }

            Arrays.stream(inputFiles.get()).forEach(file -> {
                FileParser fileParser = new FileParser(file.getAbsolutePath(), "outputPath");
                loggerMain.info(fileParser.getInputExtensionType().toString());
            });
        }
    }
}
