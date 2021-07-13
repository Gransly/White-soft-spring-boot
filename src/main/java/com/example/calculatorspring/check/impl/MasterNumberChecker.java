package com.example.calculatorspring.check.impl;

import com.example.calculatorspring.calculation.Sum;
import com.example.calculatorspring.check.InputChecker;
import com.example.calculatorspring.check.exception.MasterNumberException;
import com.example.calculatorspring.utility.UserInput;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(4)
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "validation.number", name = "master-number-check", havingValue = "true")
public class MasterNumberChecker implements InputChecker {

    private final Sum digitSum;


    @Override
    public void execute(String input) {
        int[] digits = UserInput.convertToIntArray(input);
        if(digitSum.calculateValue(digits) ==42.0){
            throw new MasterNumberException("42 sum is here!");
        }

    }
}
