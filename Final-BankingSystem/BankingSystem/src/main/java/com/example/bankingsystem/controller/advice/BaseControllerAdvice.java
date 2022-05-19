package com.example.bankingsystem.controller.advice;


import com.example.bankingsystem.exception.BaseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@RestControllerAdvice
public class BaseControllerAdvice {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> onBaseExceptionHandled(BaseException baseException) {
        return ResponseEntity.badRequest().body(new EndpointError(baseException.getMessage()));
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> onRuntimeExceptionHandled(RuntimeException baseException) {
        return ResponseEntity.badRequest().body(new EndpointError(baseException.getMessage()));
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidFormatException.class, MismatchedInputException.class})
    public ResponseEntity<?> onRuntimeMessageNotReadableException(JsonProcessingException jsonProcessingException){
        return ResponseEntity.badRequest().body(new EndpointError(jsonProcessingException.getMessage().toUpperCase(Locale.ROOT)));
    }


    public static record EndpointError(String errorMessage) {
    }
}
