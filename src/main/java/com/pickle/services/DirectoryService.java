package com.pickle.services;

import com.pickle.utility.Directory;
import com.pickle.utility.MyLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class DirectoryService {

        private String path;

        @Autowired
        public DirectoryService() {
        }

        public boolean createOutputDirectoryStructure() {
                File outputDirectory = new File(this.path);

                File rootOutputDirectory = new File(this.path + "\\" + Directory.ROOT_OUTPUT_DIRECTORY);
                String comparePath = rootOutputDirectory.getPath() + "\\" +Directory.COMPARE_DIRECTORY;
                String logsPath = rootOutputDirectory.getAbsolutePath() + "\\" + Directory.LOGS_DIRECTORY;

                String[] outputDirectoryStructure = new String[]{rootOutputDirectory.getAbsolutePath(), comparePath, logsPath};

                if (rootOutputDirectory.exists())
                        deleteDirectory(rootOutputDirectory);

                try {
                        IntStream.range(0, outputDirectoryStructure.length).forEach(index -> {
                                File directory = new File(outputDirectoryStructure[index]);
                                createDirectory(directory);
                        });
                } catch (Exception e) {
                        MyLogger.logger.error("An error occurred while creating the output directory structure" + e.getMessage());
                        return false;
                }

                if (!outputDirectory.isDirectory()){
                        MyLogger.logger.error("The output path is not a directory");
                        return false;
                }

                MyLogger.logger.info("Successfully created the output directory structure");
                return true;
        }

        public boolean createDirectory(File directory) {
                if (directory.exists())
                        this.deleteDirectory(directory);

                boolean result = directory.mkdirs();

                if (!result) {
                        MyLogger.logger.error("Failed to create the output directory");
                        return false;
                }
                return true;
        }

        public String getPath() {
                return path;
        }

        public boolean deleteDirectory(File directory) {
                if (directory.exists()) {
                        Optional<File[]> files = Optional.ofNullable(directory.listFiles());

                        files.ifPresent(value -> Arrays.stream(value).forEach(file -> {
                                boolean res = file.isDirectory() ? deleteDirectory(file) : file.delete();
                        }));
                        return directory.delete();
                }
                MyLogger.logger.error("Failed to delete the output directory");

                return false;
        }

        public void setPath(String path) {
                this.path = path;
        }
}
