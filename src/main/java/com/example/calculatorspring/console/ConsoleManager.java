package com.example.calculatorspring.console;

import com.example.calculatorspring.calculation.Calculation;
import com.example.calculatorspring.utility.UserInput;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Profile("!test")
@Component
@RequiredArgsConstructor
public class ConsoleManager implements CommandLineRunner {
    private final List<Calculation> calculationList;


    @Override
    public void run(String... args) {

        try(Scanner scanner = new Scanner(System.in);) {
            String input;
            while (true) {
                System.out.println("Number: ");
                input = scanner.next();
                if (UserInput.validate(input)) {
                    break;
                }
                System.out.println("Error, invalid number.");
            }

            int[] digits = UserInput.convertToIntArray(input);
            for (Calculation operation : calculationList) {
                double value = operation.calculateValue(digits);
                System.out.println(value);
            }
        }


    }
}
