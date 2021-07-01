package com.example.calculatorspring.calculation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Order(1)
@Component
@ConditionalOnProperty(name = "count_alg.int.min.enabled", havingValue = "true")
public class Min implements Calculation {
    @Override
    public double calculateValue(int[] array) {
        return Arrays.stream(array).min().getAsInt();
    }

}
