package com.example.calculatorspring.controller;

import com.example.calculatorspring.entity.dto.MathExpressionsDto;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import com.jupiter.tools.spring.test.postgres.annotation.meta.EnablePostgresIntegrationTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@DBRider
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@EnablePostgresIntegrationTest
class LogControllerIT {

    @Autowired
    private WebTestClient webTestClient;


    @Test
    @DataSet(cleanBefore = true, cleanAfter = true, value = "datasets/number_dataset.json")
    @ExpectedDataSet("datasets/number_dataset.json")
    void getLogResult(){

        List<MathExpressionsDto> actual = webTestClient
                .get()
                .uri("log/all")
                .exchange()

                //Act
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<MathExpressionsDto>>() {
                })
                .returnResult().getResponseBody();

        List<MathExpressionsDto> expected = new ArrayList<>();

        MathExpressionsDto dto = new MathExpressionsDto();
        dto.setId(UUID.fromString("75e5f3bb-e8ce-482c-836b-198dc7c7c177"));
        dto.setNumber(1);
        dto.setResult("1.0, 1.0, 1.0, 1.0");
        dto.setCreationDate(LocalDateTime.parse("2021-08-14 17:58:29",
                                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        expected.add(dto);

        //Assert
        assertEquals(expected, actual);
    }


    @Test
    @DataSet(cleanAfter = true, cleanBefore = true, value = "datasets/number_empty.json")
    @ExpectedDataSet(value = "datasets/number_empty.json")
    void getLogResultsFromEmptyTable() {
        //Act
        List<MathExpressionsDto> actual = webTestClient
                .get()
                .uri("log/all")
                .exchange()

                //Assert
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<MathExpressionsDto>>() {
                })
                .returnResult().getResponseBody();

        assertNotNull(actual);
        assertTrue(actual.isEmpty());
    }


    @ParameterizedTest
    @MethodSource("dataForSearchFunctionalityTest")
    @DataSet(cleanAfter = true, cleanBefore = true, value = "number_dataset_filter.json")
    @ExpectedDataSet(value = "number_dataset_filter.json")
    void getFilteredLog(LinkedMultiValueMap<String, String> args, MathExpressionsDto expected){



        List<MathExpressionsDto> filteredList= webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("log/search")
                        .queryParams(args)
                        .build())
                .exchange()

                //Assert
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<MathExpressionsDto>>() {
                })
                .returnResult().getResponseBody();

        assertNotNull(filteredList);
        assertEquals(filteredList.size(), 1);
        MathExpressionsDto actual = filteredList.get(0);
        assertThat(actual).usingRecursiveComparison().withStrictTypeChecking().isEqualTo(expected);
    }


    static Stream<Arguments> dataForSearchFunctionalityTest() {

        LinkedMultiValueMap<String, String> searchByFromDate = new LinkedMultiValueMap<String, String>() {{
            add("fromDate", "2021-08-14 17:59:29");
        }};

        LinkedMultiValueMap<String, String> searchByResult = new LinkedMultiValueMap<String, String>() {{
            add("result", "1");
        }};

        LinkedMultiValueMap<String, String> searchByInput = new LinkedMultiValueMap<String, String>() {{
            add("input", "2");
        }};

        LinkedMultiValueMap<String, String> searchByResultAndToDate = new LinkedMultiValueMap<String, String>() {{
            add("result", "1");
            add("toDate", "2022-08-14 17:58:29");
        }};


        MathExpressionsDto firstExpectedResult = new MathExpressionsDto();
        firstExpectedResult.setId(UUID.fromString("75e5f3bb-e8ce-482c-836b-198dc7c7c177"));
        firstExpectedResult.setNumber(1);
        firstExpectedResult.setResult("1");
        firstExpectedResult.setCreationDate(LocalDateTime.parse("2021-08-14 17:58:29",
                                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        MathExpressionsDto secondExpectedResult = new MathExpressionsDto();
        secondExpectedResult.setId(UUID.fromString("75e5f3bb-e8ce-482c-836b-198dc7c7c178"));
        secondExpectedResult.setNumber(2);
        secondExpectedResult.setResult("2");
        secondExpectedResult.setCreationDate(LocalDateTime.parse("2021-08-14 20:58:29",
                                                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));


        return Stream.of(
                arguments(
                        searchByFromDate,
                        secondExpectedResult),

                arguments(
                        searchByResult,
                        firstExpectedResult),

                arguments(
                        searchByInput,
                        secondExpectedResult),

                arguments(
                        searchByResultAndToDate,
                        firstExpectedResult)
                        );
    }
}