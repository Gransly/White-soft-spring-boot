package com.example.calculatorspring.aspect;

import com.example.calculatorspring.service.notification.NotificationMessage;
import com.example.calculatorspring.service.notification.TelegramBotNotification;
import com.google.common.collect.Lists;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationAspectTest {

    @Mock
    private TelegramBotNotification telegramBotNotification;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private JoinPoint joinPoint;


    @Captor
    ArgumentCaptor<NotificationMessage> messageCaptor;

    @Test
    void notifyAboutRequest() {

        //Arrange
        NotificationAspect aspect = new NotificationAspect(Lists.newArrayList(telegramBotNotification));

        when(joinPoint.getSignature().getName()).thenReturn("getStatsByParam");

        //Act
        aspect.notifyAboutRequest(joinPoint);

        //Assert
        verify(telegramBotNotification, times(1)).sendNotification(messageCaptor.capture());

        NotificationMessage actualMessage = messageCaptor.getValue();

        assertEquals(actualMessage.getMethodName(), "getStatsByParam");

    }
}