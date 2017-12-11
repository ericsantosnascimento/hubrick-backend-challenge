package com.hubrick.exceptions;

/**
 * This exception represent business exception for missing csv files
 *
 * @author eric.nascimento
 */
public class CSVFileNotFoundException extends HubrickException {

    public CSVFileNotFoundException(String message) {
        super(message);
    }

    public CSVFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
