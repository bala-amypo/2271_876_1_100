package com.example.demo.exception;

public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message); // ✅ DO NOT prefix or modify message
    }
}
