package com.example.calculatorspring.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class DailySummaryMessage {
    LocalDateTime callTime;
    long requestCount;
    int maxInput;
    int minInput;
}
