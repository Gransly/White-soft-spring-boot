package com.example.calculatorspring.calculation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Order(2)
@Component
@ConditionalOnProperty(name = "count_alg.int.max.enabled", havingValue = "true")
public class Max implements Calculation {

    @Override
    public double calculateValue(int[] array) {
        return Arrays.stream(array).max().getAsInt();
    }

}
