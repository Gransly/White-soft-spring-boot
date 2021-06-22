package com.example.calculatorspring.calculation;

public class Avg implements Calculation{

    @Override
    public Object CalculateValue(int[] array) {
        int summarize = 0;
        for (Integer number : array) {
            summarize += number;
        }
        return (float) summarize/array.length;
    }

    @Override
    public String GetDefinition(Object digit) {
        return String.format("Avg digit is %.2f", digit);
    }
}
