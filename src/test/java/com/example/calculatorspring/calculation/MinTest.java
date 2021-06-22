package com.example.calculatorspring.calculation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinTest {

    @Test
    void calculateValue() {
        //Arrange
        int[] input = new int[]{0,1,2};
        //Act
        Min minimal = new Min();

        int result = (int)minimal.CalculateValue(input);
        //Assert
        int expected = 0;
        assertEquals(expected,result);
    }
}