package com.example.calculatorspring.controller;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    void getCalculationResult(){

        List<String> actual = webTestClient
                .get()
                .uri("log/all")
                .exchange()

                //Assert
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<String>>() {
                })
                .returnResult().getResponseBody();

        List<String> expected = new ArrayList<>(Arrays.asList(
                "4.0, 4.0, 4.0, 4.0",
                "3.0, 3.0, 3.0, 3.0",
                "2.0, 2.0, 2.0, 2.0",
                "1.0, 1.0, 1.0, 1.0"
                ));

        assertEquals(expected, actual);
    }


}