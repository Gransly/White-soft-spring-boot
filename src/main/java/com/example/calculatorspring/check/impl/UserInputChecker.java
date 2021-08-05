package com.example.calculatorspring.check.impl;

import com.example.calculatorspring.check.InputChecker;
import com.example.calculatorspring.check.exception.InputForbiddenException;
import com.example.calculatorspring.utility.UserInput;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
@ConditionalOnProperty(prefix = "validation.number", name = "basic-input-check", havingValue = "true")
public class UserInputChecker implements InputChecker {
    @Override
    public void execute(String input) {
        if (!UserInput.validate(input)) {
            throw new InputForbiddenException("Error, input should contain number>0");
        }
    }
}
