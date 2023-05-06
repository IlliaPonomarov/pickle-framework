package com.pickle.parsers;

import com.pickle.utility.enums.ExtensionType;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;

public class FileParser {
    private String inputPath;
    private String outputPath;
    private String extension;

    public FileParser(String inputPath, String outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    public void createOutputFile() {
        File file = new File(this.outputPath);
        boolean isFileCreated = false;
        try {
            isFileCreated = file.createNewFile();

            if (!isFileCreated);
                throw new IOException();

        }  catch (IOException e) {
            throw new RuntimeException("\"An error occurred while creating the file:" + e.getMessage());
        }
    }

    public ExtensionType getInputExtensionType() {
        return getExtensionType(this.inputPath);
    }

    public ExtensionType getOutputExtensionType() {
        return getExtensionType(this.outputPath);
    }

    private ExtensionType getExtensionType(String filePath) {
        String extension = "";

        if (!isExist(filePath))
            throw new IllegalArgumentException("File does not exist");

        extension = Path.of(filePath).getFileName().toString().split("\\.")[1];

        return ExtensionType.getExtensionType(extension);
    }

    private boolean isExist(String path) {
        return new File(path).exists();
    }



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
