package com.example.calculatorspring.controller;

import com.example.calculatorspring.aspect.Notify;
import com.example.calculatorspring.entity.MathExpressions;
import com.example.calculatorspring.entity.SearchStats;
import com.example.calculatorspring.entity.dto.MathExpressionsDto;
import com.example.calculatorspring.service.LogService;
import com.example.calculatorspring.utility.DtoConverter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@Tag(name = "Log controller")
public class LogController {

    private final LogService logService;
    private final ModelMapper modelMapper;

    @Notify(description = "Returns all log math expressions sorted by date")
    @GetMapping("log/all")
    @Operation(summary = "Returns all log math expressions sorted by date")
    public List<MathExpressionsDto> getLog() {
        List<MathExpressions> expressions = logService.exportLogExpression();

        return expressions.stream()
                    .map(DtoConverter::convertToMathExpressionDto)
                    .collect(Collectors.toList());
    }

    @Notify(description = "Returns all log math expressions filtered by input, result and date")
    @GetMapping("log/search")
    @Operation(summary = "Returns all log math expressions filtered by input, result and date")
    public List<MathExpressionsDto> getLogBySearch( SearchStats stats){
        List<MathExpressions> expressions = logService.searchLogs(stats);
        return expressions.stream()
                          .map(DtoConverter::convertToMathExpressionDto)
                          .collect(Collectors.toList());
    }

}
