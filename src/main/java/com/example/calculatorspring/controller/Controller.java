package com.example.calculatorspring.controller;

import com.example.calculatorspring.calculation.Calculation;
import com.example.calculatorspring.utility.UserInput;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class Controller {
    private final List<Calculation> calculationList;

    @PostMapping("calculator/all")
    public List<Double> getCalculationResult(
            @RequestParam(name = "inputString") String inputString) {

        List<Double> answer = new ArrayList<>();
        int[] digits = UserInput.convertToIntArray(inputString);
        for (Calculation operation : calculationList) {
            double value = operation.calculateValue(digits);
            answer.add(value);
        }

        return answer;
    }
}
