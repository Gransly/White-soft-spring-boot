package com.example.calculatorspring.calculation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxTest {

    @Test
    void calculateValue() {
        //Arrange
        int[] input = new int[]{0,1,2};
        //Act
        Max maximal = new Max();

        int result = (int)maximal.CalculateValue(input);
        //Assert
        int expected = 2;
        assertEquals(expected,result);
    }



}