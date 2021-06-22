package com.example.calculatorspring.console;

import com.example.calculatorspring.calculation.Calculation;
import com.example.calculatorspring.calculator.Calculator;
import com.example.calculatorspring.utility.Input;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.Vector;

@Component
@AllArgsConstructor
public class ConsoleManager implements CommandLineRunner {
    Calculator calculator;
    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        while(true){
            System.out.println("Number: ");
            input= scanner.next();
            if(Input.Validate(input)){
                break;
            }
            System.out.println("Error, invalid number.");
        }

        int[] digits = Input.ConvertToIntArray(input);
        List<String> descriptions = new Vector<>();
        for (Calculation operation: calculator.getCalculationList()){
            Object value = operation.CalculateValue(digits);
            System.out.println(value);
        }

    }
}
