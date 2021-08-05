package com.example.calculatorspring.check.impl;


import com.example.calculatorspring.check.InputChecker;
import com.example.calculatorspring.check.exception.DevilNumberException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(3)
@Component
@ConditionalOnProperty(prefix = "validation.number", name = "devil-check", havingValue = "true")
public class DevilNumberChecker implements InputChecker {
    @Override
    public void execute(String input) {
        if(input.contains("666")){
            throw new DevilNumberException("Devil is above, input contain 666");
        }
    }
}
