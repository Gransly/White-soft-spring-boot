package com.example.calculatorspring.controller;

import com.example.calculatorspring.action.CalculationAction;
import com.example.calculatorspring.service.LogService;
import com.sun.jna.platform.unix.solaris.LibKstat;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Tag(name = "Calculation controller")
public class CalculationController {

    private final CalculationAction calculationAction;
    private final LogService logService;

    @PostMapping("calculator/all")
    @Operation(summary = "Returns numbers Min, Max, Avg and Sum of numbers")
    public String getCalculationResult(
            @RequestParam(name = "inputString", required = true) String inputString){

        String result = calculationAction.execute(inputString);
        logService.addLogExpression(inputString, result);
        return result;
    }
}
