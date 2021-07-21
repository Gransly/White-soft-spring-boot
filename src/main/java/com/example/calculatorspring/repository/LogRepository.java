package com.example.calculatorspring.repository;

import com.example.calculatorspring.entity.MathExpressions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LogRepository extends JpaRepository<MathExpressions, UUID> {
}
