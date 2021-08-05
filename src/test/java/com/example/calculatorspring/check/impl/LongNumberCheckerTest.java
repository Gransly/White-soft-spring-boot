package com.example.calculatorspring.check.impl;

import com.example.calculatorspring.check.exception.LongNumberException;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class LongNumberCheckerTest {
    private final LongNumberChecker checker = new LongNumberChecker();

    @Test
    void moreThenOneHundred(){
        //Arrange
        String number = String.join("", Collections.nCopies(102,"2"));

        //Act & Assert
        assertThrows(LongNumberException.class, ()->checker.execute(number));
    }

    @Test
    void exactlyOneHundred(){
        //Arrange
        String number = String.join("", Collections.nCopies(100,"2"));

        //Act & Assert
        assertThrows(LongNumberException.class, ()->checker.execute(number));
    }


    @Test
    void lessThenOneHundred(){
        //Arrange
        String number = String.join("", Collections.nCopies(66,"2"));

        //Act & Assert
        assertDoesNotThrow(()->checker.execute(number));
    }
}