package com.example.calculatorspring.service;

import com.example.calculatorspring.entity.MathExpressions;
import com.example.calculatorspring.entity.QMathExpressions;
import com.example.calculatorspring.repository.LogRepository;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository repository;

    public void addLogExpression(String number, String result){

        MathExpressions expression = new MathExpressions();

        expression.setNumber(Integer.parseInt(number));
        expression.setResult(result.replace('\n',' '));

        repository.save(expression);
    }

    public List<MathExpressions> exportLogExpression(){


        return repository.findAll();
    }

    public List<MathExpressions> exportLogExpressionByAfterDate(LocalDateTime afterDate){

        return Lists.newArrayList(repository.findAll(QMathExpressions.mathExpressions.creationDate.after(afterDate)));
    }

    public List<MathExpressions> exportLogExpressionByBeforeDate(LocalDateTime beforeDate){

        return Lists.newArrayList(repository.findAll(QMathExpressions.mathExpressions.creationDate.before(beforeDate)));
    }

    public List<MathExpressions> exportLogExpressionByBetweenDate(LocalDateTime beforeDate, LocalDateTime afterDate){

        return Lists.newArrayList(repository.findAll(QMathExpressions.mathExpressions.creationDate.between(beforeDate,afterDate)));
    }


}
