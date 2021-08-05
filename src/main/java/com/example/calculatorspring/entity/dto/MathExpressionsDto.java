package com.example.calculatorspring.entity.dto;

import com.example.calculatorspring.entity.MathExpressions;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.xml.bind.v2.schemagen.episode.SchemaBindings;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class MathExpressionsDto {

    @Id
    private UUID id;

    private int number;

    private String result;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime creationDate;

}
