package com.example.calculatorspring.service;

import com.example.calculatorspring.calculation.Calculation;
import com.example.calculatorspring.utility.UserInput;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalculatorService {

    private final List<Calculation> calculationList;

    public String getCalculations(String inputString){


        List<Double> answer = new ArrayList<>();
        int[] digits = UserInput.convertToIntArray(inputString);
        for (Calculation operation : calculationList) {
            double value = operation.calculateValue(digits);
            answer.add(value);
        }

        return StringUtils.join(answer, ", ");

    }
}
