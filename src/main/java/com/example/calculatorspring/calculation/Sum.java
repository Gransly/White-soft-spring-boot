package com.example.calculatorspring.calculation;

import org.springframework.stereotype.Component;

@Component
public class Sum implements Calculation{
    @Override
    public Object CalculateValue(int[] array) {
        int summarize = 0;
        for (Integer number : array) {
            summarize += number;
        }
        return  summarize;
    }

}
