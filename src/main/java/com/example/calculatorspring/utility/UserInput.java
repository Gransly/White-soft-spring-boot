package com.example.calculatorspring.utility;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserInput {
    public static boolean validate(String input) {
        return !input.isEmpty() && input.matches("[0-9]+");
    }

    public int[] convertToIntArray(String input) {
        return input.chars().map(Character::getNumericValue).toArray();
    }
}
