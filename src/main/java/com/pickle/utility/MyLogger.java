package com.pickle.utility;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * The InputNotFoundException class for the input path
 * @version 1.0
 * @since 2023-05-07
 * @author Illia Ponomarov
 */

public interface MyLogger {

    /**
     * Logger is object of Logger class
     */
    Logger logger = LogManager.getLogger(MyLogger.class);

}
