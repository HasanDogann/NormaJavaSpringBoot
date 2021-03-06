package com.example.bankingsystem.controller.advice;


import com.example.bankingsystem.exception.BaseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class BaseControllerAdvice extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return ResponseEntity.badRequest().body(new EndpointError(ex.getCause().getLocalizedMessage()));
    }


    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> onBaseExceptionHandled(BaseException baseException) {
        return ResponseEntity.badRequest().body(new EndpointError(baseException.getMessage()));
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<?> onRuntimeExceptionHandled(RuntimeException baseException) {
        return ResponseEntity.badRequest().body(new EndpointError(baseException.getMessage()));
    }

    @ExceptionHandler(value = { MismatchedInputException.class})
    public ResponseEntity<?> onRuntimeMessageNotReadableException(JsonProcessingException jsonProcessingException){
        return ResponseEntity.badRequest().body(new EndpointError(jsonProcessingException.getMessage()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e) {
        return  ResponseEntity.badRequest().body(new EndpointError(e.getMessage()));
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<?> processJsonInvalidFormatException(InvalidFormatException e) {
        return ResponseEntity.badRequest().body(new EndpointError(e.getMessage()));
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.badRequest().body(e.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList());
    }

    public static record EndpointError(String errorMessage) {
    }
}
