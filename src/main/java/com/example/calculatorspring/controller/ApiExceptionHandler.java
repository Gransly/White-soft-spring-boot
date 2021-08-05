package com.example.calculatorspring.controller;

import com.example.calculatorspring.check.exception.*;
import com.example.calculatorspring.controller.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(InputForbiddenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleInputForbiddenException(RuntimeException ex){
        return new ErrorDto(ex.getMessage());
    }

    @ExceptionHandler(DevilNumberException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleDevilNumberException(RuntimeException ex){
        return new ErrorDto(ex.getMessage());
    }

    @ExceptionHandler(LongNumberException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleLongNumberException(RuntimeException ex){
        return new ErrorDto(ex.getMessage());
    }

    @ExceptionHandler(MasterNumberException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleMasterNumberException(RuntimeException ex){
        return new ErrorDto(ex.getMessage());
    }

}
