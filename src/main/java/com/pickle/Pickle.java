package com.pickle;
import com.pickle.parsers.FileParser;
import com.pickle.services.FileService;
import com.pickle.utility.MyLogger;
import org.apache.commons.cli.CommandLine;


import java.io.File;
import java.util.Arrays;
import java.util.Optional;


public class Pickle {

    public static void main(String[] args) {
        CommandLine myArgumentParser;
        String inputPath = "C:\\Users\\hp\\IdeaProjects\\pickle-framework\\src\\test\\java\\files\\yaml\\test1.yaml";
        String outputPath = "";
        Optional<File[]> inputFiles;
        FileService fileService = new FileService();

//        if (args.length == 0) {
//            MyLogger.logger.error("No arguments provided");
//            System.exit(1);
//        }


        MyLogger.logger.info("Starting Pickle ...");



        myArgumentParser = MyArgumentParser.build(args);

        if ((myArgumentParser.hasOption("i") || myArgumentParser.hasOption("input")) && (myArgumentParser.hasOption("o") || myArgumentParser.hasOption("output"))) {
            System.out.println("Input");
            inputFiles = Optional.ofNullable(new File(inputPath).listFiles());

            if (inputFiles.isEmpty()) {
                MyLogger.logger.error("No files found in the input directory");
                System.exit(1);
            }

            Arrays.stream(inputFiles.get()).forEach(file -> {
                FileParser fileParser = new FileParser(file.getAbsolutePath(), "outputPath");
                MyLogger.logger.info(fileParser.getInputExtensionType().toString());
            });
        }
    }
}
