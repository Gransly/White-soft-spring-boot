package com.example.calculatorspring.controller;

import com.example.calculatorspring.action.CalculationAction;
import com.example.calculatorspring.aspect.Notify;
import com.example.calculatorspring.service.LogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Tag(name = "Calculation controller")
public class CalculationController {

    private final CalculationAction calculationAction;
    private final LogService logService;


    @Notify(description = "Returns numbers Min, Max, Avg and Sum of numbers")
    @PostMapping("calculator/all")
    @Operation(summary = "Returns numbers Min, Max, Avg and Sum of numbers")
    public String getCalculationResult(
            @RequestParam(name = "inputString") String inputString) {

        String result = calculationAction.execute(inputString);
        logService.addLogExpression(inputString, result);
        return result;
    }

}
