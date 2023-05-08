package com.pickle.utility.exceptions.argumentsExceptions;

/**
 * The InputNotFoundException class for the input path
 * @version 1.0
 * @since 2023-05-07
 * @author Illia Ponomarov
 */
public class InputOrOutputNotFoundException extends RuntimeException{

    /**
     * The InputNotFoundException constructor
     * @param message the message
     */
    public InputOrOutputNotFoundException(String message) {
        super(message);
    }
}
