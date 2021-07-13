package com.example.calculatorspring.check.impl;

import com.example.calculatorspring.check.exception.DevilNumberException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DevilNumberCheckerTest {

    private final DevilNumberChecker checker = new DevilNumberChecker();
    @Test
    void containsDevilNumber() {
        //Arrange
        String number = "666";

        //Act & Assert
        assertThrows(DevilNumberException.class, () -> checker.execute(number));
    }

    @Test
    void notContainsDevilNumber() {
        //Arrange
        String number = "123";

        //Act & Assert
        assertDoesNotThrow(()->checker.execute(number));
    }
}