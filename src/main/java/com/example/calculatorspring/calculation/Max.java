package com.example.calculatorspring.calculation;

import org.springframework.stereotype.Component;

@Component
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


}
