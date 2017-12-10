package com.hubrick.exceptions;

public class CSVFileNotFoundException extends HubrickException {

    public CSVFileNotFoundException(String message) {
        super(message);
    }

    public CSVFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
