package com.example.calculatorspring.calculation;

import org.springframework.stereotype.Component;

@Component
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


}
