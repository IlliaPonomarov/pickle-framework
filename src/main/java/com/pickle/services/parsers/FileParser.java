package com.pickle.services.parsers;

import com.pickle.utility.MyLogger;
import com.pickle.utility.enums.ExtensionType;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

/**
 * The InputNotFoundException class for the input path
 * @version 1.0
 * @since 2023-05-07
 * @author Illia Ponomarov
 */

@Service
public class FileParser {
    private String inputPath;
    private String outputPath;
    private String extension;

    public FileParser() {

    }

    /**
     * The FileParser constructor
     * @param inputPath the input path
     * @param outputPath the output path
     */

    public FileParser(String inputPath, String outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }


    public String getInputFileName() {
        return getFileName(this.inputPath);
    }

    public boolean removeFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    public String getOutputFileName() {
        return getFileName(this.outputPath);
    }

    public String getFileName(String path) {
        return Path.of(path).getFileName().toString();
    }

    public ExtensionType getInputExtensionType() {
        return getExtensionType(this.inputPath);
    }

    public ExtensionType getOutputExtensionType() {
        return getExtensionType(this.outputPath);
    }

    public String getInputFileNameWithoutExtension() {
        return getFileNameWithoutExtension(this.inputPath);
    }

    public String getOutputFileNameWithoutExtension() {
        return getFileNameWithoutExtension(this.outputPath);
    }

    /**
     * The getFileNameWithoutExtension method
     * @param path the path
     * @return String
     */
    public String getFileNameWithoutExtension(String path) {
        String fileName = getFileName(path);
        return fileName.substring(0, fileName.lastIndexOf("."));
    }

    private ExtensionType getExtensionType(String filePath) {
        ExtensionType extension = ExtensionType.NONE;
        String extensionString = "";

        try {
            if (!isExist(filePath))
                throw new IllegalArgumentException("File does not exist");

            extensionString = filePath.substring(filePath.lastIndexOf(".") + 1);

            extension = ExtensionType.getExtensionType(extensionString);

        } catch (ArrayIndexOutOfBoundsException e) {
            MyLogger.logger.error("File does not have an extension" + e.getMessage());
        } catch (IllegalArgumentException e) {
            MyLogger.logger.error("File does not exist" + e.getMessage());
        } catch (Exception e) {
            MyLogger.logger.error("An error occurred while getting the file extension" + e.getMessage());
        }

        return extension;
    }

    public InputStream getInputStream() {
        try {
            return new FileInputStream(this.inputPath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /** not tested yet */
    @Deprecated
    public boolean isSOAP() {
        File file = new File(this.inputPath);

        try{
            String fileContent = Files.readString(file.toPath(), StandardCharsets.UTF_8);

            if (fileContent.contains("Envelope") || fileContent.contains("Fault") || fileContent.contains("Body") || fileContent.contains("soap") || fileContent.contains("SOAP"))
                return true;
        } catch (Exception e) {
            MyLogger.logger.error("An error occurred while checking if the file is SOAP" + e.getMessage());
        }
        return false;
    }

    /** not tested yet */
    @Deprecated
    public boolean isREST() {
        File file = new File(this.inputPath);

        try{
            String fileContent = Files.readString(file.toPath(), StandardCharsets.UTF_8).toLowerCase();

            if (fileContent.contains("get") || fileContent.contains("post") || fileContent.contains("put") || fileContent.contains("delete") || fileContent.contains("patch") || fileContent.contains("rest"))
                return true;
        } catch (Exception e) {
            MyLogger.logger.error("An error occurred while checking if the file is REST" + e.getMessage());
        }
        return false;
    }


    private boolean isExist(String path) {
        return new File(path).exists();
    }

    /**
     * The getExtension method
     * @return String
     */

    public String getInputPath() {
        return inputPath;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FileParser))
            return false;

        FileParser that = (FileParser) o;
        return Objects.equals(inputPath, that.inputPath) && Objects.equals(outputPath, that.outputPath) && Objects.equals(extension, that.extension);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inputPath, outputPath, extension);
    }

    @Override
    public String toString() {
        return "FileParser(inputPath=" + inputPath + ", outputPath=" + outputPath + ", extension=" + extension + ")";
    }


}
