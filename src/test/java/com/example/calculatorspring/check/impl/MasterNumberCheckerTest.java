package com.example.calculatorspring.check.impl;

import com.example.calculatorspring.calculation.Sum;
import com.example.calculatorspring.check.exception.MasterNumberException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MasterNumberCheckerTest {

   private final Sum  digitsSumMock = mock(Sum.class);
   private final MasterNumberChecker checker = new MasterNumberChecker(digitsSumMock);

   @Test
    void containMasterNumber(){
      //Arrange
      int[] expectedDigits = {9, 9, 9, 9, 6};
      when(digitsSumMock.calculateValue(expectedDigits)).thenReturn(42.0);

      //Act & assert
      assertThrows(MasterNumberException.class, () -> checker.execute("99996"));
      verify(digitsSumMock).calculateValue(expectedDigits);
   }

   @Test
   void notContainMasterNumber(){
      //Arrange
      int[] expectedDigits = {1, 9, 9, 9, 6};
      when(digitsSumMock.calculateValue(expectedDigits)).thenReturn(34.0);

      //Act & assert
      assertDoesNotThrow(() -> checker.execute("19996"));
      verify(digitsSumMock).calculateValue(expectedDigits);
   }


}