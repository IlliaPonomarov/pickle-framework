package com.pickle;
import com.pickle.models.OperationTestCase;
import com.pickle.services.communications.RESTCommunication;
import com.pickle.services.parsers.EndpointParser;
import com.pickle.services.parsers.FileParser;
import com.pickle.services.parsers.parserFactories.EndpointParserFactory;
import com.pickle.services.parsers.parserFactories.ParserFactory;
import com.pickle.services.ArgsService;
import com.pickle.services.parsers.fileParsers.DirectoryService;
import com.pickle.services.parsers.fileParsers.FileService;
import com.pickle.utility.MyLogger;
import com.pickle.utility.enums.ExtensionType;
import org.apache.commons.cli.CommandLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.*;

/**
 * The InputNotFoundException class for the input path
 * @version 1.0
 * @since 2023-05-07
 * @author Illia Ponomarov
 */

@SpringBootApplication
public class Pickle implements CommandLineRunner {

    private final ArgsService argsService;
    private final DirectoryService directoryService;
    private final Map<UUID, Map<UUID, ? extends OperationTestCase>> operations = new HashMap<>();


    /**
     * The Pickle constructor
     * @param argsService the args service
     * @param directoryService the directory service
     */

    @Autowired
    public Pickle(ArgsService argsService, DirectoryService directoryService) {
        this.argsService = argsService;
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
        String inputPath = "C:/Users/hp/IdeaProjects/pickle-framework/src/test/java/files/yaml";
        String outputAbsolutePath = "C:/Users/hp/IdeaProjects/pickle-framework/src/test/java/testF/";

        args[0] = "-i";
        args[1] = inputPath;
        args[2] = "-o";
        args[3] = outputAbsolutePath;

//        if (args.length == 0) {
//            MyLogger.logger.error("No arguments provided");
//            System.exit(1);
//        }


        MyLogger.logger.info("Starting Pickle ...");

        Arrays.stream(args).forEach(arg -> MyLogger.logger.info("ARGUMENT: " + arg) );

        myArgumentParser = MyArgumentParser.build(args);
        argsService.setMyArgumentParser(myArgumentParser);

//        String inputPath = argsService.getInputPath();
//        String outputAbsolutePath = argsService.getOutputPath();

        try {
            if (argsService.hasInputAndOutputPath()) {
                directoryService.setPath(outputAbsolutePath);
                directoryService.createOutputDirectoryStructure();

                MyLogger.logger.info(String.format("INPUT PATH: %s", inputPath));
                MyLogger.logger.info(String.format("OUTPUT PATH: %s", outputAbsolutePath));
                Optional<File[]> listOfFiles = Optional.ofNullable(new File(inputPath).listFiles());
                RESTCommunication restCommunication = null;

                if (listOfFiles.isEmpty()) {
                    MyLogger.logger.error("No files found in the input directory");
                    System.exit(1);
                }

                Arrays.stream(listOfFiles.get()).forEach(file -> {
                    String inputAbsolutePath = file.getAbsolutePath();
                    FileParser fileParser = new FileParser(inputAbsolutePath, outputAbsolutePath);
                    FileService fileService = new FileService(fileParser, directoryService);

                    MyLogger.logger.info(String.format("Parsing file: %s", file.getName()));
                    MyLogger.logger.info("INPUT FILE NAME: " + fileParser.getInputFileName());
                    MyLogger.logger.info("INPUT FILE EXTENSION: " + fileParser.getInputExtensionType());

                    fileService.createOutputFileStructure();

                    Optional<EndpointParserFactory> parser = Optional.ofNullable(new ParserFactory(fileParser).getEndpointParserFactory());
                    ExtensionType extensionType = fileParser.getInputExtensionType();;
                    Optional<? extends EndpointParser> endpointParser = Optional.empty();

                    if (extensionType.equals(ExtensionType.NONE) || parser.isEmpty()) {
                        MyLogger.logger.error(String.format("\nError: Unsupported file extension\nFile: %s",  file.getName()));
                        return;
                    }

                    switch (extensionType) {
                        case YAML -> endpointParser = Optional.ofNullable(parser.get().createYamlEndpointParser(fileParser));
                        case JSON -> endpointParser = Optional.ofNullable(parser.get().createJsonEndpointParser(fileParser));
                        case XML  -> endpointParser = Optional.ofNullable(parser.get().createXmlEndpointParser(fileParser));
                    }

                    endpointParser.ifPresent(endpoint -> {
                        operations.put(UUID.randomUUID(), endpoint.createTestCase()) ;
                    });

                });

                restCommunication = new RESTCommunication(operations);
                restCommunication.sendRequest();
            }
        } catch (NullPointerException e) {
            MyLogger.logger.error("Error: " + e.getMessage());
            System.exit(1);
        }
    }

}
