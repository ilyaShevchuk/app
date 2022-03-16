package com.stadium.app.exceptions;

public class BadParamsException extends RuntimeException {
    public BadParamsException(String message) {
        super(message);
    }
}