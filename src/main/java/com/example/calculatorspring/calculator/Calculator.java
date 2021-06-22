package com.example.calculatorspring.calculator;

import com.example.calculatorspring.calculation.Calculation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Getter
@AllArgsConstructor
public class Calculator {
    List<Calculation> calculationList;
}
