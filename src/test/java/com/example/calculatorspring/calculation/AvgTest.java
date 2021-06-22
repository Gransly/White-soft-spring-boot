package com.example.calculatorspring.calculation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AvgTest {

    @Test
    void calculateValue() {//Arrange
        int[] input = new int[]{0,1,2};
        //Act
        Avg average = new Avg();

        float result = (float)average.CalculateValue(input);
        //Assert
        float expected = 1;
        assertEquals(expected,result);
    }
}