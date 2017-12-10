package com.hubrick.exceptions;

public class CSVWriteException extends RuntimeException {

    public CSVWriteException() {
        super();
    }

    public CSVWriteException(String message) {
        super(message);
    }

    public CSVWriteException(String message, Throwable cause) {
        super(message, cause);
    }

    public CSVWriteException(Throwable cause) {
        super(cause);
    }
}
