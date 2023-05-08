package com.pickle;
import com.pickle.parsers.FileParser;
import com.pickle.services.ArgsService;
import com.pickle.services.DirectoryService;
import com.pickle.services.FileService;
import com.pickle.utility.MyLogger;
import org.apache.commons.cli.CommandLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.io.File;
import java.util.Arrays;
import java.util.Optional;

/**
 * The InputNotFoundException class for the input path
 * @version 1.0
 * @since 2023-05-07
 * @author Illia Ponomarov
 */

@SpringBootApplication
public class Pickle implements CommandLineRunner {

    private final ArgsService argsService;

    private final FileService fileService;

    private final DirectoryService directoryService;

    /**
     * The Pickle constructor
     * @param argsService the args service
     * @param fileService the file service
     * @param directoryService the directory service
     */
    @Autowired
    public Pickle(ArgsService argsService, FileService fileService, DirectoryService directoryService) {
        this.argsService = argsService;
        this.fileService = fileService;
        this.directoryService = directoryService;
    }

    public static void main(String... args) {
        SpringApplication.run(Pickle.class, args);
    }

    /**
     * The run method for Pickle
     * @param args the command line arguments
     */
    @Override
    public void run(String... args) {
        args = new String[4];

        // Label
        System.out.println("\n" +
                "\033[32m" +
                "██████╗ ██╗ ██████╗██╗  ██╗██╗     ███████╗\n" +
                "██╔══██╗██║██╔════╝██║ ██╔╝██║     ██╔════╝\n" +
                "██████╔╝██║██║     █████╔╝ ██║     █████╗  \n" +
                "██╔═══╝ ██║██║     ██╔═██╗ ██║     ██╔══╝  \n" +
                "██║     ██║╚██████╗██║  ██╗███████╗███████╗\n" +
                "╚═╝     ╚═╝ ╚═════╝╚═╝  ╚═╝╚══════╝╚══════╝\n" +
                "                                           \n" +
                "\033[0m");

        CommandLine myArgumentParser;
        String inputPath = "C:\\Users\\hp\\IdeaProjects\\pickle-framework\\src\\test\\java\\files\\yaml";
        String outputPath = "C:\\Users\\hp\\IdeaProjects\\pickle-framework\\src\\test\\java\\files\\yaml";
        Optional<File[]> inputFiles;

        args[0] = "-i";
        args[1] = inputPath;
        args[2] = "-o";
        args[3] = outputPath;

        MyLogger.logger.info("Starting Pickle ...");

        myArgumentParser = MyArgumentParser.build(args);
        argsService.setMyArgumentParser(myArgumentParser);

        try {
            if (argsService.hasInputAndOutputPath()) {
                directoryService.setPath(inputPath);
                directoryService.createOutputDirectoryStructure();

                MyLogger.logger.info(String.format("INPUT PATH: %s", inputPath));
                MyLogger.logger.info(String.format("OUTPUT PATH: %s", outputPath));
                var listOfFiles = Optional.ofNullable(new File(inputPath).listFiles());

                if (listOfFiles.isEmpty()) {
                    MyLogger.logger.error("No files found in the input directory");
                    System.exit(1);
                }

                Arrays.stream(listOfFiles.get()).forEach(file -> {
                    FileParser fileParser = new FileParser(file.getAbsolutePath(), outputPath);
                    FileService fileService = new FileService(fileParser, directoryService);
                    MyLogger.logger.info(String.format("Parsing file: %s", file.getName()));
                    MyLogger.logger.info("INPUT FILE NAME: " + fileParser.getInputFileName());
                    MyLogger.logger.info("INPUT FILE EXTENSION: " + fileParser.getInputExtensionType());
                    fileService.createOutputFileStructure();
                });
            }
        } catch (NullPointerException e) {
            MyLogger.logger.error("No input or output path provided");
            System.exit(1);
        }
    }
}
