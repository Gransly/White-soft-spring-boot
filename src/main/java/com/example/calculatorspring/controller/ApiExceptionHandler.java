package com.example.calculatorspring.controller;

import com.example.calculatorspring.check.InputForbiddenException;
import com.example.calculatorspring.controller.dto.ErrorDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {InputForbiddenException.class})
    public ResponseEntity<Object> spaForbidden(RuntimeException exception) {
        return new ResponseEntity<Object>(
                new ErrorDto(exception.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
