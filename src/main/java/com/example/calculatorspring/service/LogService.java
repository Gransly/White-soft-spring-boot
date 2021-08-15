package com.example.calculatorspring.service;

import com.example.calculatorspring.entity.MathExpressions;
import com.example.calculatorspring.entity.QMathExpressions;
import com.example.calculatorspring.entity.SearchStats;
import com.example.calculatorspring.filter.OptionalPredicate;
import com.example.calculatorspring.repository.LogRepository;
import com.google.common.collect.Lists;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository repository;

    @Transactional
    public void addLogExpression(String number, String result){

        MathExpressions expression = new MathExpressions();

        expression.setNumber(Integer.parseInt(number));
        expression.setResult(result.replace('\n',' '));

        repository.save(expression);
    }

    @Transactional(readOnly = true)
    public List<MathExpressions> exportLogExpression(){
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public List<MathExpressions> searchLogs(SearchStats stats){

        Predicate predicate = OptionalPredicate.builder()
                                               .optionalAnd(stats.getInput(), QMathExpressions.mathExpressions.number::eq)
                                               .optionalAnd(stats.getResult(), QMathExpressions.mathExpressions.result::eq)
                                               .optionalAnd(stats.getFromDate(), QMathExpressions.mathExpressions.creationDate::goe)
                                               .optionalAnd(stats.getToDate(), QMathExpressions.mathExpressions.creationDate::loe)
                                               .build();

        return Lists.newArrayList(repository.findAll(predicate));
    }


}
