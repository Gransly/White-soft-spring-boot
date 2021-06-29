package com.example.calculatorspring.calculation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SumTest {

    Sum summarize = new Sum();
    @Test
    void calculateValue() {

        //Arrange
        int[] input = new int[]{0, 1, 2};

        //Act
        int result = (int) summarize.calculateValue(input);

        //Assert
        int expected = 3;
        assertEquals(expected, result);
    }

}