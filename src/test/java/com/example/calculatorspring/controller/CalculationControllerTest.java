package com.example.calculatorspring.controller;

import com.example.calculatorspring.controller.dto.ErrorDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CalculationControllerTest {

    private MvcResult mvcResult;
    private ErrorDto errorDto;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private String expectedMessage;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getCalculationValidResult() throws Exception {
        //Arrange
        String inputString = "1234";

        //Act
        mvcResult = mockMvc.perform(MockMvcRequestBuilders
                                            .post("/calculator/all")
                                            .param("inputString", inputString))
                           //Assert
                           .andExpect(status().isOk())
                           .andReturn();
        String expected = "[1.0,4.0,10.0,2.5]";
        assertEquals(expected, mvcResult.getResponse().getContentAsString());
    }

    @Test
    void getCalculationInvalidInputNumbers() throws Exception {
        //Arrange
        String inputString = "-1234";

        //Act
        mvcResult = mockMvc.perform(MockMvcRequestBuilders
                                            .post("/calculator/all")
                                            .param("inputString", inputString))
                           //Assert
                           .andExpect(status().isBadRequest())
                           .andReturn();
        errorDto = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorDto.class);
        expectedMessage = "Wrong user input";
        assertEquals(errorDto.getMessage(), expectedMessage);
    }

    @Test
    void getCalculationInvalidInputSymbols() throws Exception {
        //Arrange
        String inputString = "-aswaf$1";

        //Act
        mvcResult = mockMvc.perform(MockMvcRequestBuilders
                                            .post("/calculator/all")
                                            .param("inputString", inputString))
                           //Assert
                           .andExpect(status().isBadRequest())
                           .andReturn();
        errorDto = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorDto.class);
        expectedMessage = "Wrong user input";
        assertEquals(errorDto.getMessage(), expectedMessage);
    }

    @Test
    void getCalculationInvalidInputEmptyString() throws Exception {
        //Arrange
        String inputString = "";

        //Act
        mvcResult = mockMvc.perform(MockMvcRequestBuilders
                                            .post("/calculator/all")
                                            .param("inputString", inputString))
                           //Assert
                           .andExpect(status().isBadRequest())
                           .andReturn();
        errorDto = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorDto.class);
        expectedMessage = "Wrong user input";
        assertEquals(errorDto.getMessage(), expectedMessage);
    }

}