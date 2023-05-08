package com.pickle.parsers;

import com.pickle.services.FileService;
import com.pickle.utility.MyLogger;
import com.pickle.utility.enums.ExtensionType;
import org.springframework.stereotype.Service;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.IntStream;

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
        if (!(o instanceof FileParser that)) return false;
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
