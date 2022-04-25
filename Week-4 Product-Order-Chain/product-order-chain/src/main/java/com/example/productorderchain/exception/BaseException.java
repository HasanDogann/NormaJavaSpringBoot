package com.example.productorderchain.exception;

public class BaseException extends RuntimeException {

    private final String message;

    public BaseException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
