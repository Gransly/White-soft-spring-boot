package com.example.calculatorspring.utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInputTest {


    @Test
    void validateEmpty() {

        //Arrange
        String input = "";

        //Act
        boolean result = UserInput.validate(input);

        //Assert
        assertFalse( result);
    }

    @Test
    void validateSpaceBar() {

        //Arrange
        String input = " ";

        //Act
        boolean result = UserInput.validate(input);

        //Assert
        assertFalse( result);
    }


    @Test
    void validatePositiveNumbers() {

        //Arrange
        String input = "012345678";


        //Act
        boolean result = UserInput.validate(input);


        //Assert
        assertTrue(result);
    }

    @Test
    void validateInvalidNumber() {

        //Arrange
        String number = "-1";

        //Act
        boolean validationResult = UserInput.validate(number);

        //Assert
        assertFalse(validationResult);
    }
    @Test
    void validateZero() {

        //Arrange
        String number = "0";

        //Act
        boolean validationResult = UserInput.validate(number);

        //Assert
        assertTrue(validationResult);
    }
    @Test
    void validateNumberStartingWithZero() {

        //Arrange
        String number = "012";

        //Act
        boolean validationResult = UserInput.validate(number);

        //Assert
        assertTrue(validationResult);
    }


    @Test
    void convertToIntArray() {

        //Arrange
        String input = "012";

        //Act
        int[] result = UserInput.convertToIntArray(input);

        //Assert
        int[] expected = new int[]{0, 1, 2};
        assertArrayEquals(expected, result);
    }
}