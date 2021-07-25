package com.example.calculatorspring.controller;

import com.example.calculatorspring.entity.MathExpressions;
import com.example.calculatorspring.service.LogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Log controller")
public class LogController {

    private final LogService logService;

    @GetMapping("log/all")
    @Operation(summary = "Returns all log math expressions sorted by date")
    public List<MathExpressions> getLog() {
        return logService.exportLogExpression();
    }


    @GetMapping("log/byAfterDate")
    @Operation(summary = "Returns all log math expressions sorted by date")
    public List<MathExpressions> getLogByAfterDateExcluding(@RequestParam("date")
                                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return logService.exportLogExpressionByAfterDate(date);
    }

    @GetMapping("log/byBeforeDate")
    @Operation(summary = "Returns all log math expressions sorted by date")
    public List<MathExpressions> getLogByBeforeDateExcluding(@RequestParam("date")
                                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return logService.exportLogExpressionByBeforeDate(date);
    }

    @GetMapping("log/byBetweenDate")
    @Operation(summary = "Returns all log math expressions sorted by date")
    public List<MathExpressions> getLogByBetweenDateExcluding(@RequestParam("beforeDate")
                                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime beforeDate,
                                                              @RequestParam("afterDate")
                                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime afterDate) {
        return logService.exportLogExpressionByBetweenDate(beforeDate, afterDate);
    }

    @GetMapping("log/byNumber")
    @Operation(summary = "Returns all log math expressions sorted by date")
    public List<MathExpressions> getLogByNumberExcluding(@RequestParam(name = "min") Integer min,
                                                         @RequestParam(name = "max") Integer max) {
        return logService.exportLogExpressionByInputExcluding(min, max);
    }
}
