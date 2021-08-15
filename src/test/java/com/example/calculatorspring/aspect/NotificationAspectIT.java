package com.example.calculatorspring.aspect;

import com.example.calculatorspring.entity.NotificationMessage;
import com.example.calculatorspring.service.notification.TelegramBotNotification;
import com.jupiter.tools.spring.test.postgres.annotation.meta.EnablePostgresIntegrationTest;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@EnablePostgresIntegrationTest
@ExtendWith(SoftAssertionsExtension.class)
class NotificationAspectIT {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private TelegramBotNotification telegramBotNotification;

    @Captor
    ArgumentCaptor<NotificationMessage> messageCaptor;

    @Test
    void testSendRequestInputForbiddenError(SoftAssertions softAssertions) {
        //Act
        String actualResponse =webTestClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("/calculator/all/")
                        .queryParam("inputString", "123")
                        .build())
                .exchange()

                //Assert
                .expectStatus().isOk()
                .expectBody(String.class).returnResult()
                .getResponseBody();

        String expectedResponse="1.0, 3.0, 6.0, 2.0";

        assertEquals(expectedResponse, actualResponse);

        verify(telegramBotNotification, times(1))
                .sendNotification(messageCaptor.capture());



        NotificationMessage actualMessage = messageCaptor.getValue();
        softAssertions.assertThat(actualMessage.getDescription()).isEqualTo("Returns numbers Min, Max, Avg and Sum of numbers");
        softAssertions.assertThat(actualMessage.getMethodName()).isEqualTo("getCalculationResult");

    }
}