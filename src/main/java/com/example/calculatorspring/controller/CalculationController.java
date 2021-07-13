package com.example.calculatorspring.controller;

import com.example.calculatorspring.action.CalculationAction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Tag(name = "Calculation controller")
public class CalculationController {

    private final CalculationAction calculationAction;

    @PostMapping("calculator/all")
    @Operation(summary = "Returns numbers Min, Max, Avg and Sum of numbers")
    public List<Double> getCalculationResult(
            @RequestParam(name = "inputString", required = true) String inputString){
        return calculationAction.execute(inputString);
    }
}
