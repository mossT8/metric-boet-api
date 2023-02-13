package com.javatpoint.common.exception;

/**
 * Custom exception class used to handle any errors that occur while working with metrics
 */
public class MetricsException extends Exception {

    /**
     * Constructs a new exception with the specified detail message
     * @param message the detail message
     */
    public MetricsException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public MetricsException(String message, Throwable cause) {
        super(message, cause);
    }
}
