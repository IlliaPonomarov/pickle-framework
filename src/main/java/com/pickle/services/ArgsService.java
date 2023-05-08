package com.pickle.services;


import com.pickle.utility.MyLogger;
import com.pickle.utility.exceptions.argumentsExceptions.InputOrOutputNotFoundException;
import com.pickle.utility.exceptions.argumentsExceptions.InputNotFoundException;
import com.pickle.utility.exceptions.argumentsExceptions.OutputNotFoundException;
import org.apache.commons.cli.CommandLine;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The ArgsService class for the command line arguments
 * @version 1.0
 * @author Illia Ponomarov
 * @since 2023-05-07
 */
@Service
public class ArgsService {

    private CommandLine myArgumentParser;

    public ArgsService() {
        this.myArgumentParser = null;
    }

    public ArgsService(CommandLine myArgumentParser) {
        this.myArgumentParser = myArgumentParser;
    }

    public boolean hasInputAndOutputPath(){
        String input = myArgumentParser.getOptionValue("i");
        String output = myArgumentParser.getOptionValue("o");

        return hasInputAndOutputPath(Optional.ofNullable(input), Optional.ofNullable(output));
    }

    /**
     * The hasInputAndOutputPath method for ArgsService
     * @return boolean value
     */

    public boolean hasInputAndOutputPath(Optional<String> input, Optional<String> output) {
        boolean hasInputPath = hasInputPath(input);
        boolean hasOutputPath = hasOutputPath(output);

        try {
            if (!hasInputPath || !hasOutputPath)
                throw new InputOrOutputNotFoundException("No input or output path provided");
        } catch (InputOrOutputNotFoundException e) {
            MyLogger.logger.error(String.format("Your input and output paths are blank or empty. %s", e.getMessage()));
            return false;
        }

        return true;
    }

    /**
     * The hasInputPath method for ArgsService
     * @return boolean value
     */

    private boolean hasInputPath(Optional<String> input) {

        try {
            if (input.isEmpty() || input.get().isBlank())
                throw new InputNotFoundException("No input path provided");
        } catch (InputNotFoundException e) {
            MyLogger.logger.error(String.format("Your input path is blank or empty. %s", e.getMessage()));
            return false;
        }

        return true;
    }

    /**
     * The hasOutputPath method for ArgsService
     * @return boolean value
     */

    private boolean hasOutputPath(Optional<String> output) {

        try {
            if ( output.isEmpty() || output.get().isBlank())
                throw new OutputNotFoundException("No output path provided");
        } catch (OutputNotFoundException e) {
            MyLogger.logger.error(String.format("Your output path is blank or empty. %s", e.getMessage()));
            return false;
        }
        return true;
    }

    public String getInputPath() {
        return myArgumentParser.getOptionValue("i");
    }

    public String getOutputPath() {
        return myArgumentParser.getOptionValue("o");
    }

    public CommandLine getMyArgumentParser() {
        return myArgumentParser;
    }

    public void setMyArgumentParser(CommandLine myArgumentParser) {
        this.myArgumentParser = myArgumentParser;
    }



}
