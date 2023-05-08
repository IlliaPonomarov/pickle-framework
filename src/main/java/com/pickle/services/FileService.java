package com.pickle.services;

import com.pickle.parsers.FileParser;
import com.pickle.utility.Directory;
import com.pickle.utility.MyLogger;
import com.pickle.utility.enums.ExtensionType;
import com.pickle.utility.exceptions.argumentsExceptions.FileName;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Service for handling files
 * @author Illia Ponomarov
 * @version 1.0
 * @since 2023-05-08
 * @see FileParser
 * @see DirectoryService
 */
@Service
public class FileService {

    private final FileParser fileParser;
    private final DirectoryService directoryService;

    public FileService(FileParser fileParser, DirectoryService directoryService) {
        this.fileParser = fileParser;
        this.directoryService = directoryService;
    }

    /**
     * Creates the output file structure
     * @return true if the output file structure was created successfully
     */
    public boolean createOutputFileStructure() {
        ExtensionType inputExtensionType = fileParser.getInputExtensionType();

        if (inputExtensionType.equals(ExtensionType.NONE)) {
            return false;
        }
        // Get the input file name without extension and get the extension of the input file
        String inputFileNameWithoutExtension = fileParser.getInputFileNameWithoutExtension() + "-result";
        String inputExtension = fileParser.getInputExtensionType().getExtension();

        // Get path to actual test case directory
        String compareDirectoryPath = fileParser.getOutputPath() + "\\" + Directory.ROOT_OUTPUT_DIRECTORY + "\\" + Directory.COMPARE_DIRECTORY;
        String pathToActualTestCase = compareDirectoryPath + "\\" + inputFileNameWithoutExtension;

        // Create the actual test case directory
        File actualTestCaseDirectory = new File(pathToActualTestCase);
        directoryService.createDirectory(actualTestCaseDirectory);

        // Delete directory if it already exists
        String outputFilePath = pathToActualTestCase + "\\";


        // Create the actual response file
        String actualResponseFilePath = pathToActualTestCase + "\\" + FileName.ACTUAL_RESPONSE_FILE_NAME + "." + inputExtension;
        createNewFile(actualResponseFilePath);

        // Create the expected response file
        String expectedResponseFilePath = pathToActualTestCase + "\\" + FileName.EXPECTED_RESPONSE_FILE_NAME + "." + inputExtension;
        createNewFile(expectedResponseFilePath);


        /*
            Copy the input file to the actual response file
         */
        try {
            Path sourceFilePath = Paths.get(fileParser.getInputPath());
            Path destinationDirectoryPath = Paths.get(pathToActualTestCase);

            Path destinationFilePath = destinationDirectoryPath.resolve(sourceFilePath.getFileName());

            Files.copy(sourceFilePath, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            MyLogger.logger.error("An error occurred while copying the input file to the output file: " + e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * Deletes the output file structure
     * @return true if the output file structure was deleted successfully
     */

    public boolean deleteFile(String path) {
        File file = new File(path);
        if (file.exists() && !file.delete()) {
            MyLogger.logger.error("Failed to delete the output file");
            return false;
        }
        return true;
    }

    /**
     * Creates a new file
     * @return true if the file was created successfully
     */

    public boolean createNewFile(String path) {
        File file = new File(path);
        try {
            if (!file.createNewFile()) {
                MyLogger.logger.error("Failed to create the output file");
                return false;
            }
        } catch (IOException e) {
            MyLogger.logger.error("An error occurred while creating the output file: " + e.getMessage());
            return false;
        }
        return true;
    }
}