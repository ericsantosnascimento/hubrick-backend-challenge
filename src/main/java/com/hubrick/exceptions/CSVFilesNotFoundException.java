package com.hubrick.exceptions;

public class CSVFilesNotFoundException extends RuntimeException {

    public CSVFilesNotFoundException() {
        super();
    }

    public CSVFilesNotFoundException(String message) {
        super(message);
    }

    public CSVFilesNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CSVFilesNotFoundException(Throwable cause) {
        super(cause);
    }
}
