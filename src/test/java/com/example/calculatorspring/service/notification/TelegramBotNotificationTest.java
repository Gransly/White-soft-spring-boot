package com.example.calculatorspring.service.notification;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TelegramBotNotificationTest {

    @Mock
    private TelegramBotNotification telegramBotNotification;

    @Captor
    ArgumentCaptor<String> stringCaptor;

    @Test
    void sendRequestNotification() {
        //Arrange
        LocalDateTime dateTime = LocalDateTime.now();

        NotificationMessage messageArg = NotificationMessage.builder()
                                                            .callTime(dateTime)
                                                            .methodName("method")
                                                            .build();

        doCallRealMethod().when(telegramBotNotification).sendNotification(any(NotificationMessage.class));

        //Act
        telegramBotNotification.sendNotification(messageArg);

        //Assert
        String expectedMessage = "\uD83D\uDD58 Call time: " +
                         messageArg.getCallTime() +
                         '\n' +
                         "Error method name: " +
                         messageArg.getMethodName();


        verify(telegramBotNotification, times(1))
                .sendMessageToBot(stringCaptor.capture());

        assertEquals(expectedMessage, stringCaptor.getValue());

    }

}