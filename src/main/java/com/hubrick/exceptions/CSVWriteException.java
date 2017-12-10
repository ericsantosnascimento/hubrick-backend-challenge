package com.hubrick.exceptions;

public class CSVWriteException extends HubrickException {

    public CSVWriteException(String message) {
        super(message);
    }

    public CSVWriteException(String message, Throwable cause) {
        super(message, cause);
    }
}
