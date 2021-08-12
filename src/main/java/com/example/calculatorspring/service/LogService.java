package com.example.calculatorspring.service;

import com.example.calculatorspring.entity.MathExpressions;
import com.example.calculatorspring.entity.QMathExpressions;
import com.example.calculatorspring.repository.LogRepository;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
    public List<MathExpressions> exportLogExpressionByAfterDate(LocalDateTime afterDate){

        return Lists.newArrayList(repository.findAll(QMathExpressions.mathExpressions.creationDate.after(afterDate)));
    }

    @Transactional(readOnly = true)
    public List<MathExpressions> exportLogExpressionByBeforeDate(LocalDateTime beforeDate){

        return Lists.newArrayList(repository.findAll(QMathExpressions.mathExpressions.creationDate.before(beforeDate)));
    }

    @Transactional(readOnly = true)
    public List<MathExpressions> exportLogExpressionByBetweenDate(LocalDateTime beforeDate, LocalDateTime afterDate){

        return Lists.newArrayList(repository.findAll(QMathExpressions.mathExpressions.creationDate.between(beforeDate,afterDate)));
    }

    @Transactional(readOnly = true)
    public long getNumberOfRequest(){
        return  repository.count();
    }

    public int getMaxInput(){
        int maxNumber = -1;
        List<MathExpressions> records = exportLogExpression();
        Optional<MathExpressions> numberStatsOptional = records.stream().max(Comparator.comparing(MathExpressions::getNumber));
        if (numberStatsOptional.isPresent()) {
            maxNumber = numberStatsOptional.get().getNumber();
        }

        return maxNumber;
    }

    public int getMinInput(){
        int minNumber = -1;
        List<MathExpressions> records = exportLogExpression();
        Optional<MathExpressions> numberStatsOptional = records.stream().min(Comparator.comparing(MathExpressions::getNumber));
        if (numberStatsOptional.isPresent()) {
            minNumber = numberStatsOptional.get().getNumber();
        }

        return minNumber;
    }



}
