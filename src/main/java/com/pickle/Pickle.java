package com.pickle;
import com.pickle.parsers.FileParser;
import com.pickle.services.ArgsService;
import com.pickle.services.FileService;
import com.pickle.utility.MyLogger;
import org.apache.commons.cli.CommandLine;


import java.io.File;
import java.util.Arrays;
import java.util.Optional;


public class Pickle {

    public static void main(String[] args) {
        CommandLine myArgumentParser;
        String inputPath = "";
        String outputPath = "";
        ArgsService argsService;
        Optional<File[]> inputFiles;
        FileService fileService = new FileService();

        if (args.length == 0) {
            MyLogger.logger.error("No arguments provided");
            System.exit(1);
        }

        MyLogger.logger.info("Starting Pickle ...");

        myArgumentParser = MyArgumentParser.build(args);
        argsService = new ArgsService(myArgumentParser);

        myArgumentParser.getOptionValue("i");

        try {
            if (argsService.hasInputAndOutputPath()) {
                MyLogger.logger.info(String.format("INPUT PATH: %s", myArgumentParser.getOptionValue("i")));
                MyLogger.logger.info(String.format("OUTPUT PATH: %s", myArgumentParser.getOptionValue("o")));
                inputFiles = Optional.ofNullable(new File(inputPath).listFiles());

                Arrays.stream(inputFiles.get()).forEach(file -> {
                    FileParser fileParser = new FileParser(file.getAbsolutePath(), "outputPath");
                    MyLogger.logger.info(fileParser.getInputExtensionType().toString());
                });
            }
        } catch (NullPointerException e) {
            MyLogger.logger.error("No input or output path provided");
            System.exit(1);
        }
    }
}
