package com.example.calculatorspring.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MathExpressions {

    @Id
    @GeneratedValue
    private UUID id;

    private int number;

    private String result;

    private LocalDateTime creationDate;

    @PrePersist
    public void prePersist() {
        creationDate = LocalDateTime.now();
    }

}
