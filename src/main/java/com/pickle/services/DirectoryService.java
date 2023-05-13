package com.pickle.services;

import com.pickle.utility.Directory;
import com.pickle.utility.MyLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * The DirectoryService class for the directory service
 * @version 1.0
 * @since 2023-05-08
 * @author Illia Ponomarov
 * @see com.pickle.utility.Directory
 * @see com.pickle.utility.MyLogger
 */

@Service
public class DirectoryService {

        private String path;

        @Autowired
        public DirectoryService() {
        }

        /*
         * Method @method createOutputDirectoryStructure is method for creating the output ( test case ) directory structure.
         */
        public boolean createOutputDirectoryStructure() {
                File outputDirectory = new File(this.path);

                // The root output directory
                File rootOutputDirectory = new File(this.path + "/" + Directory.ROOT_OUTPUT_DIRECTORY);
                String comparePath = rootOutputDirectory.getPath() + "/" +Directory.COMPARE_DIRECTORY;
                String logsPath = rootOutputDirectory.getAbsolutePath() + "/" + Directory.LOGS_DIRECTORY;

                String[] outputDirectoryStructure = new String[]{rootOutputDirectory.getAbsolutePath(), comparePath, logsPath};

                // Delete the output directory if it already exists
                if (rootOutputDirectory.exists())
                        deleteDirectory(rootOutputDirectory);

                // Create the output directory structure
                try {
                        IntStream.range(0, outputDirectoryStructure.length).forEach(index -> {
                                File directory = new File(outputDirectoryStructure[index]);
                                createDirectory(directory);
                        });
                } catch (Exception e) {
                        MyLogger.logger.error("An error occurred while creating the output directory structure" + e.getMessage());
                        return false;
                }

                // Check if the output directory structure was created successfully
                if (!outputDirectory.isDirectory()){
                        MyLogger.logger.error("The output path is not a directory");
                        return false;
                }

                MyLogger.logger.info("Successfully created the output directory structure");
                return true;
        }

        /*
            * Method @method createDirectory is method for creating the directory.
         */
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

        /*
         * Method @method deleteDirectory is method for deleting the directory.
         */

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

        /*
         * Method @method getPath is method for getting the path.
         */
        public String getPath() {
                return path;
        }

        /*
         * Method @method setPath is method for setting the path.
         */
        public void setPath(String path) {
                this.path = path;
        }
}
