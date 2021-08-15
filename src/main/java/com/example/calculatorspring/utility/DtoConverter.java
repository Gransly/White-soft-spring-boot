package com.example.calculatorspring.utility;

import com.example.calculatorspring.entity.MathExpressions;
import com.example.calculatorspring.entity.dto.MathExpressionsDto;
import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;

@UtilityClass
public class DtoConverter {

    public static MathExpressionsDto convertToMathExpressionDto(MathExpressions expression) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(expression, MathExpressionsDto.class);
    }

    public static MathExpressions convertToMathExpressionEntity(MathExpressionsDto expressionsDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(expressionsDto, MathExpressions.class);
    }
}
