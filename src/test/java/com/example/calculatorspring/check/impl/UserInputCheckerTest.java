package com.example.calculatorspring.check.impl;

import com.example.calculatorspring.check.exception.InputForbiddenException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInputCheckerTest {
    private final UserInputChecker checker = new UserInputChecker();


    @Test
    void negativeNumber(){
        //Arrange
        String number = "-123";

        //Act & Assert
        assertThrows(InputForbiddenException.class,()-> checker.execute(number) );
    }


    @Test
    void invalidSymbolInput(){
        //Arrange
        String number = "asdasdasd";

        //Act & Assert
        assertThrows(InputForbiddenException.class,()-> checker.execute(number) );
    }

    @Test
    void validInput(){
        //Arrange
        String number = "123123";

        //Act & Assert
        assertDoesNotThrow(()-> checker.execute(number) );
    }

}