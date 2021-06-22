package com.example.calculatorspring.utility;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class InputTest {

    @Test
    void valiate() {

        //Arrange
        String input = "012345678";
        //Act

        boolean result = Input.Validate(input);
        //Assert
        boolean expected = true;
        assertEquals(expected,result);
    }

    @Test
    void convertToIntArray() {
        //Arrange
        String input = "012";
        //Act

        int[] result = Input.ConvertToIntArray(input);
        //Assert
        int[] expected = new int[]{0,1,2};
        assertArrayEquals(expected,result);
    }
}