package com.example.calculatorspring.calculation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinTest {

    Min minimal = new Min();
    @Test
    void calculateValue() {

        //Arrange
        int[] input = new int[]{0, 1, 2};

        //Act
        int result = (int) minimal.calculateValue(input);

        //Assert
        int expected = 0;
        assertEquals(expected, result);
    }
}