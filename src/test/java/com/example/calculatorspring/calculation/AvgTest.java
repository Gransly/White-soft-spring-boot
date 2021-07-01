package com.example.calculatorspring.calculation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AvgTest {

    private Avg average = new Avg();

    @Test
    void calculateValue() {
        //Arrange
        int[] input = new int[]{0, 1, 2};

        //Act
        double result = average.calculateValue(input);

        //Assert
        double expected = 1;
        assertEquals(expected, result);
    }
}