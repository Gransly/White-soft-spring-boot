package com.example.calculatorspring.service.notification;

import com.example.calculatorspring.entity.NotificationMessage;
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
                                                            .methodName("Name")
                                                            .description("Desc")
                                                            .exceptionName("")
                                                            .build();

        doCallRealMethod().when(telegramBotNotification).sendNotification(any(NotificationMessage.class));

        //Act
        telegramBotNotification.sendNotification(messageArg);

        //Assert

        String expectedMessage = "Call time: " +
                         messageArg.getCallTime() +
                         "\nMethod name: " +
                         messageArg.getMethodName()+
                         "\nMethod name: " +
                         messageArg.getDescription()+
                         "\nSuccessful: Yes";


        verify(telegramBotNotification, times(1))
                .sendMessageToBot(stringCaptor.capture());

        assertEquals(expectedMessage, stringCaptor.getValue());

    }

}