package com.example.calculatorspring.aspect;

import com.example.calculatorspring.service.notification.NotificationMessage;
import com.example.calculatorspring.service.notification.TelegramBotNotification;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
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

import java.util.Collections;

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
    void testSendRequestDevilError(SoftAssertions softly) {
        //Act
        String actual = webTestClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("/calculator/all/")
                        .queryParam("inputString", "666")
                        .build())
                .exchange()

                //Assert
                .expectStatus().isBadRequest()
                .expectBody(String.class)
                .returnResult().getResponseBody();

        String expected = "{\"message\":\"Devil is above, input contain 666\"}";

        assertEquals(expected, actual);
        verify(telegramBotNotification, times(1))
                .sendNotification(messageCaptor.capture());

        NotificationMessage actualMessage = messageCaptor.getValue();

        assertEquals(actualMessage.getMethodName(),"handleDevilNumberException");
    }

    @Test
    void testSendRequestInputForbiddenError(SoftAssertions softly) {
        //Act
        String actual = webTestClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("/calculator/all/")
                        .queryParam("inputString", "asdasd")
                        .build())
                .exchange()

                //Assert
                .expectStatus().isBadRequest()
                .expectBody(String.class)
                .returnResult().getResponseBody();

        String expected = "{\"message\":\"Error, input should contain number>0\"}";

        assertEquals(expected, actual);
        verify(telegramBotNotification, times(1))
                .sendNotification(messageCaptor.capture());

        NotificationMessage actualMessage = messageCaptor.getValue();

        assertEquals(actualMessage.getMethodName(),"handleInputForbiddenException");
    }

    @Test
    void testSendRequestLongNumberError(SoftAssertions softly) {

        //Arrange
        String number = String.join("", Collections.nCopies(100, "2"));
        //Act
        String actual = webTestClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("/calculator/all/")
                        .queryParam("inputString", number)
                        .build())
                .exchange()

                //Assert
                .expectStatus().isBadRequest()
                .expectBody(String.class)
                .returnResult().getResponseBody();

        String expected = "{\"message\":\"Input number contains more then 100 digits\"}";

        assertEquals(expected, actual);
        verify(telegramBotNotification, times(1))
                .sendNotification(messageCaptor.capture());

        NotificationMessage actualMessage = messageCaptor.getValue();

        assertEquals(actualMessage.getMethodName(),"handleLongNumberException");
    }

    @Test
    void testSendRequestMasterNumberError(SoftAssertions softly) {
        //Act
        String actual = webTestClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("/calculator/all/")
                        .queryParam("inputString", "99996")
                        .build())
                .exchange()

                //Assert
                .expectStatus().isBadRequest()
                .expectBody(String.class)
                .returnResult().getResponseBody();

        String expected = "{\"message\":\"42 sum is here!\"}";

        assertEquals(expected, actual);
        verify(telegramBotNotification, times(1))
                .sendNotification(messageCaptor.capture());

        NotificationMessage actualMessage = messageCaptor.getValue();

        assertEquals(actualMessage.getMethodName(),"handleMasterNumberException");
    }
}