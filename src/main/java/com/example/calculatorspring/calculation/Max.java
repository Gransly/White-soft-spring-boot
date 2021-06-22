package com.example.calculatorspring.calculation;

public class Max implements Calculation{

    @Override
    public Object CalculateValue(int[] array) {
        int maxInt = 0;
        for (Integer number : array) {
            if (maxInt < number) {
                maxInt = number;
            }
        }
        return  maxInt;
    }

    @Override
    public String GetDefinition(Object digit) {
        return String.format("Max: %d", digit);
    }
}
