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

import java.time.LocalDateTime;
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

    @Notify
    @GetMapping("log/search")
    public List<MathExpressionsDto> getLogBySearch( SearchStats stats){

        List<MathExpressions> expressions = logService.searchLogs(stats);


        return expressions.stream()
                          .map(DtoConverter::convertToMathExpressionDto)
                          .collect(Collectors.toList());
    }

    @Notify(description = "Returns all log math expressions sorted by date")
    @GetMapping("log/byAfterDate")
    @Operation(summary = "Returns all log math expressions sorted by date")
    public List<MathExpressions> getLogByAfterDateExcluding(@RequestParam("date") LocalDateTime date) {
        return logService.exportLogExpressionByAfterDate(date);
    }

    @Notify(description ="Returns all log math expressions sorted by date")
    @GetMapping("log/byBeforeDate")
    @Operation(summary = "Returns all log math expressions sorted by date")
    public List<MathExpressions> getLogByBeforeDateExcluding(@RequestParam("date") LocalDateTime date) {
        return logService.exportLogExpressionByBeforeDate(date);
    }

    @Notify(description = "Returns all log math expressions sorted by date")
    @GetMapping("log/byBetweenDate")
    @Operation(summary = "Returns all log math expressions sorted by date")
    public List<MathExpressions> getLogByBetweenDateExcluding(@RequestParam("beforeDate")
                                                               LocalDateTime beforeDate,
                                                              @RequestParam("afterDate") LocalDateTime afterDate) {
        return logService.exportLogExpressionByBetweenDate(beforeDate, afterDate);
    }


}
