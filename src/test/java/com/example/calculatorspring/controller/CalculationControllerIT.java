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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CalculationControllerIT {

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
        String expected = "1.0, 4.0, 10.0, 2.5";
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
        expectedMessage = "Error, input should contain number>0";
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
        expectedMessage = "Error, input should contain number>0";
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
        expectedMessage = "Error, input should contain number>0";
        assertEquals(errorDto.getMessage(), expectedMessage);
    }

    @Test
    void getCalculationDevilNumber() throws Exception {
        //Arrange
        String inputString = "666";

        //Act
        String actual = mockMvc.perform(MockMvcRequestBuilders
                                            .post("/calculator/all")
                                            .param("inputString", inputString))
                           //Assert
                           .andExpect(status().isBadRequest())
                           .andReturn().getResponse().getContentAsString();

        ErrorDto errorDto = objectMapper.readValue(actual, ErrorDto.class);

        String expected = "Devil is above, input contain 666";
        assertEquals(errorDto.getMessage(), expected);
    }

    @Test
    void getCalculationLongNumber() throws Exception {
        //Arrange
        String inputString = "123123123123143243243242342344121231" +
                             "2312312312312312312312312312313233234234234234234" +
                             "23423423423423423423423423423423423";

        //Act
        String actual = mockMvc.perform(MockMvcRequestBuilders
                                                .post("/calculator/all")
                                                .param("inputString", inputString))
                               //Assert
                               .andExpect(status().isBadRequest())
                               .andReturn().getResponse().getContentAsString();

        ErrorDto errorDto = objectMapper.readValue(actual, ErrorDto.class);

        String expected = "Input number contains more then 100 digits";
        assertEquals(errorDto.getMessage(), expected);
    }

    @Test
    void getCalculationMasterNumber() throws Exception {
        //Arrange
        String inputString = "999942";

        //Act
        String actual = mockMvc.perform(MockMvcRequestBuilders
                                                .post("/calculator/all")
                                                .param("inputString", inputString))
                               //Assert
                               .andExpect(status().isBadRequest())
                               .andReturn().getResponse().getContentAsString();

        ErrorDto errorDto = objectMapper.readValue(actual, ErrorDto.class);

        String expected = "42 sum is here!";
        assertEquals(errorDto.getMessage(), expected);
    }
}