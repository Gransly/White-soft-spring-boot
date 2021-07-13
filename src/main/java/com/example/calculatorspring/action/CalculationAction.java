package com.example.calculatorspring.action;

import com.example.calculatorspring.check.InputChecker;
import com.example.calculatorspring.check.impl.UserInputChecker;
import com.example.calculatorspring.service.CalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CalculationAction {

    private final CalculatorService calculatorService;
    private final List<InputChecker> numberValidators;

    public List<Double> execute(String inputString)
    {
        numberValidators.forEach(validator -> validator.execute(inputString));
        return calculatorService.getCalculations(inputString);
    }
}
