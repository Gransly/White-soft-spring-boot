package com.example.calculatorspring.controller;

import com.example.calculatorspring.calculation.Calculation;
import com.example.calculatorspring.check.impl.UserInputChecker;
import com.example.calculatorspring.utility.UserInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
@Tag(name = "Calculation controller")
public class CalculationController {
    private final List<Calculation> calculationList;
    UserInputChecker userInputChecker = new UserInputChecker();

    @PostMapping("calculator/all")
    @Operation(summary = "Returns numbers Min, Max, Avg and Sum of numbers")
    public List<Double> getCalculationResult(
            @RequestParam(name = "inputString", required = true) String inputString) throws Exception {
        userInputChecker.execute(inputString);


        List<Double> answer = new ArrayList<>();
        int[] digits = UserInput.convertToIntArray(inputString);
        for (Calculation operation : calculationList) {
            double value = operation.calculateValue(digits);
            answer.add(value);
        }

        return answer;
    }
}
