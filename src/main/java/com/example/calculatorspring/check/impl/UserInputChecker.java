package com.example.calculatorspring.check.impl;

import com.example.calculatorspring.check.InputChecker;
import com.example.calculatorspring.check.InputForbiddenException;
import com.example.calculatorspring.utility.UserInput;
import org.springframework.stereotype.Component;

@Component
public class UserInputChecker implements InputChecker {
    @Override
    public void execute(String input) {
        if (!UserInput.validate(input)) {
            throw new InputForbiddenException("Wrong user input");
        }
    }
}
