package com.example.calculatorspring.utility;

import com.example.calculatorspring.entity.MathExpressions;
import com.example.calculatorspring.entity.dto.MathExpressionsDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DtoConverterTest {

    @Test
    void convertToDto() {

        //Arrange
        UUID uuid = UUID.randomUUID();
        LocalDateTime dateTime = LocalDateTime.now();
        MathExpressions entity = new MathExpressions();
        entity.setId(uuid);
        entity.setNumber(123);
        entity.setResult("1,2,3,4");
        entity.setCreationDate(dateTime);

        //Act
        MathExpressionsDto dto = DtoConverter.convertToDto(entity);

        //Assert
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getNumber(), dto.getNumber());
        assertEquals(entity.getResult(), dto.getResult());
        assertEquals(entity.getCreationDate(), dto.getCreationDate());
    }

    @Test
    void convertToEntity() {

        //Arrange
        MathExpressionsDto dto = new MathExpressionsDto();
        UUID uuid = UUID.randomUUID();
        LocalDateTime dateTime = LocalDateTime.now();
        dto.setId(uuid);
        dto.setNumber(123);
        dto.setResult("1,2,3,4");
        dto.setCreationDate(dateTime);

        //Act
        MathExpressions entity = DtoConverter.convertToEntity(dto);

        //Assert
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getNumber(), dto.getNumber());
        assertEquals(entity.getResult(), dto.getResult());
        assertEquals(entity.getCreationDate(), dto.getCreationDate());
    }
}