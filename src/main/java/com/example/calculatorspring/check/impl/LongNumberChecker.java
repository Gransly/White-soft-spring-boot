package com.example.calculatorspring.check.impl;

import com.example.calculatorspring.check.InputChecker;
import com.example.calculatorspring.check.exception.LongNumberException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Component
@ConditionalOnProperty(prefix = "validation.number", name = "long-check", havingValue = "true")
public class LongNumberChecker implements InputChecker {
    @Override
    public void execute(String input) {
        if(input.length()>=100){
            throw new LongNumberException("Input number contains more then 100 digits");

        }

    }
}
