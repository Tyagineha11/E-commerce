package com.example.e_commerce_website.exception;

public class BadTokenException extends RuntimeException {

    public BadTokenException(String message) {
        super(message);
    }

    public BadTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}