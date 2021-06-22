package com.example.calculatorspring.calculation;

public class Min implements Calculation{
    @Override
    public Object CalculateValue(int[] array) {
        int minInt = 10;
        for (Integer number : array) {
            if (minInt > number) {
                minInt = number;
            }
        }
        return minInt;
    }

    @Override
    public String GetDefinition(Object digit) {
        return String.format("Min: %d", digit);
    }
}
