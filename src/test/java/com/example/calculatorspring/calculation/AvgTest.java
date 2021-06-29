package com.example.calculatorspring.calculation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AvgTest {

    Avg average = new Avg();
    @Test
    void calculateValue() {

        //Arrange
        int[] input = new int[]{0, 1, 2};

        //Act
        float result = (float) average.calculateValue(input);

        //Assert
        float expected = 1;
        assertEquals(expected, result);
    }
}