package com.example.calculatorspring.aspect;

import com.example.calculatorspring.check.exception.InputForbiddenException;
import com.example.calculatorspring.entity.NotificationMessage;
import com.example.calculatorspring.service.notification.TelegramBotNotification;
import com.google.common.collect.Lists;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SoftAssertionsExtension.class)
class NotificationAspectTest {

    @Mock
    private TelegramBotNotification telegramBotNotification;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private ProceedingJoinPoint joinPoint;

    @Mock
    private Notify notify;

    @Captor
    ArgumentCaptor<NotificationMessage> messageCaptor;

    @Test
    void notifyAboutRequest(SoftAssertions softly) throws Throwable {

        //Arrange
        NotificationAspect aspect = new NotificationAspect(Lists.newArrayList(telegramBotNotification));

        when(joinPoint.getSignature().getName()).thenReturn("Name");
        when(notify.description()).thenReturn("Description");

        //Act
        aspect.notifyAboutRequest(joinPoint, notify);

        //Assert
        verify(telegramBotNotification, times(1)).sendNotification(messageCaptor.capture());

        NotificationMessage actualMessage = messageCaptor.getValue();

        softly.assertThat(actualMessage.getDescription()).isEqualTo("Description");
        softly.assertThat(actualMessage.getMethodName()).isEqualTo("Name");

    }

    @Test
    void notifyAboutExceptionThrow(SoftAssertions softly) throws Throwable {
        //Arrange
        NotificationAspect aspect = new NotificationAspect(Lists.newArrayList(telegramBotNotification));

        when(joinPoint.getSignature().getName()).thenReturn("Name");
        when(joinPoint.proceed()).thenThrow(new InputForbiddenException("Error, input should contain number>0"));

        when(notify.description()).thenReturn("Description");

        //Act & assert
        assertThrows(InputForbiddenException .class, () -> aspect.notifyAboutRequest(joinPoint, notify));
        verify(telegramBotNotification, times(1))
                .sendNotification(messageCaptor.capture());

        NotificationMessage actualMessage = messageCaptor.getValue();

        softly.assertThat(actualMessage.getDescription()).isEqualTo("Description");
        softly.assertThat(actualMessage.getMethodName()).isEqualTo("Name");
        softly.assertThat(actualMessage.getExceptionName()).isEqualTo("Error, input should contain number>0");
    }
}