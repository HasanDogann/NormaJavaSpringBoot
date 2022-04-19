package com.example.weatherapiv1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
@Slf4j
public class WeatherControllerAdvice extends ResponseEntityExceptionHandler {

    //This method handle exceptions as ConstraintViolation in WeatherController class
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> onConstraintViolationExceptionHandle(ConstraintViolationException exception) {
        return ResponseEntity.badRequest().body(exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList());
    }
    }