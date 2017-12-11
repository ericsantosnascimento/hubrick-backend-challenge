package com.hubrick.exceptions;

/**
 * This exceptions represent business exceptions related to csv writing
 *
 * @author eric.nascimento
 */
public class CSVWriteException extends HubrickException {

    public CSVWriteException(String message) {
        super(message);
    }

    public CSVWriteException(String message, Throwable cause) {
        super(message, cause);
    }
}
