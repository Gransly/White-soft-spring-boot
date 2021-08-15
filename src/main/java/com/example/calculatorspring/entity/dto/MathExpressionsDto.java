package com.example.calculatorspring.entity.dto;

import lombok.Data;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class MathExpressionsDto {

    @Id
    private UUID id;

    private int number;

    private String result;

    private LocalDateTime creationDate;

}
