package com.example.calculatorspring.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MathExpressions {

    @Id
    @GeneratedValue
    private UUID id;

    private int number;

    private String result;

    private LocalDateTime createDate;

    @PrePersist
    public void prePersist() {
        createDate = LocalDateTime.now();
    }

}
