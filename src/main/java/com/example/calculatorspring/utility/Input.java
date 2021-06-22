package com.example.calculatorspring.utility;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Input {
    public static boolean Validate(String input){
        return !input.isEmpty() && input.matches("[0-9]+");
    }

    public int[] ConvertToIntArray(String input){
        return input.chars().map(Character::getNumericValue).toArray();
    }
}
