package com.example.calculatorspring.controller;

import com.example.calculatorspring.entity.dto.MathExpressionsDto;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import com.jupiter.tools.spring.test.postgres.annotation.meta.EnablePostgresIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

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
        UUID uuid = UUID.fromString("75e5f3bb-e8ce-482c-836b-198dc7c7c177");
        LocalDateTime dateTime = LocalDateTime.parse("2021-07-27 15:45:44.254",
                                                     DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        dto.setId(uuid);
        dto.setNumber(1);
        dto.setResult("1.0, 1.0, 1.0, 1.0");
        dto.setCreationDate(dateTime);
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


    @Test
    @DataSet(cleanBefore = true, cleanAfter = true, value = "datasets/number_dataset_date.json")
    @ExpectedDataSet("datasets/number_dataset_date.json")
    void getLogResultAfterDate(){
        //Arrange
        String date = "2021-07-27T16:45:44.254";


        List<MathExpressionsDto> actual = webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("log/byAfterDate/") //Base-path for invoking the 3rd party service.
                        .queryParam("date",date)
                        .build())
                .exchange()

                //Act
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<MathExpressionsDto>>() {
                })
                .returnResult().getResponseBody();

        List<MathExpressionsDto> expected = new ArrayList<>();
        MathExpressionsDto dto = new MathExpressionsDto();
        UUID uuid = UUID.fromString("75e5f3bb-e8ce-482c-836b-198dc7c7c179");
        LocalDateTime dateTime = LocalDateTime.parse("2021-07-27 17:45:44.254",
                                                     DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        dto.setId(uuid);
        dto.setNumber(1);
        dto.setResult("1.0, 1.0, 1.0, 1.0");
        dto.setCreationDate(dateTime);
        expected.add(dto);

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    @DataSet(cleanBefore = true, cleanAfter = true, value = "datasets/number_dataset_date.json")
    @ExpectedDataSet("datasets/number_dataset_date.json")
    void getLogResultBeforeDate(){
        //Arrange
        String date = "2021-07-27T16:45:44.254";


        List<MathExpressionsDto> actual = webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("log/byBeforeDate/") //Base-path for invoking the 3rd party service.
                        .queryParam("date",date)
                        .build())
                .exchange()

                //Act
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<MathExpressionsDto>>() {
                })
                .returnResult().getResponseBody();

        List<MathExpressionsDto> expected = new ArrayList<>();
        MathExpressionsDto dto = new MathExpressionsDto();
        UUID uuid = UUID.fromString("75e5f3bb-e8ce-482c-836b-198dc7c7c177");
        LocalDateTime dateTime = LocalDateTime.parse("2021-07-27 15:45:44.254",
                                                     DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        dto.setId(uuid);
        dto.setNumber(1);
        dto.setResult("1.0, 1.0, 1.0, 1.0");
        dto.setCreationDate(dateTime);
        expected.add(dto);

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    @DataSet(cleanBefore = true, cleanAfter = true, value = "datasets/number_dataset_date.json")
    @ExpectedDataSet("datasets/number_dataset_date.json")
    void getLogResultBetweenDate(){
        //Arrange
        String dateBefore = "2021-07-27T15:45:44.255";
        String dateAfter= "2021-07-27T17:45:44.253";


        List<MathExpressionsDto> actual = webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("log/byBetweenDate/") //Base-path for invoking the 3rd party service.
                        .queryParam("beforeDate",dateBefore)
                        .queryParam("afterDate",dateAfter)
                        .build())
                .exchange()

                //Act
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<MathExpressionsDto>>() {
                })
                .returnResult().getResponseBody();

        List<MathExpressionsDto> expected = new ArrayList<>();
        MathExpressionsDto dto = new MathExpressionsDto();
        UUID uuid = UUID.fromString("75e5f3bb-e8ce-482c-836b-198dc7c7c178");
        LocalDateTime dateTime = LocalDateTime.parse("2021-07-27 16:45:44.254",
                                                     DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        dto.setId(uuid);
        dto.setNumber(1);
        dto.setResult("1.0, 1.0, 1.0, 1.0");
        dto.setCreationDate(dateTime);
        expected.add(dto);

        //Assert
        assertEquals(expected, actual);
    }

}