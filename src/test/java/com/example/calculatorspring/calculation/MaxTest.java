package com.example.calculatorspring.calculation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxTest {

    private Max maximal = new Max();

    @Test
    void calculateValue() {
        //Arrange
        int[] input = new int[]{0, 1, 2};

        //Act
        int result = (int) maximal.calculateValue(input);

        //Assert
        int expected = 2;
        assertEquals(expected, result);
    }

}