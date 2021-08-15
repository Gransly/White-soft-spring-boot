package com.example.calculatorspring.aspect;

import com.example.calculatorspring.check.exception.InputForbiddenException;
import com.example.calculatorspring.entity.NotificationMessage;
import com.example.calculatorspring.notifier.TelegramNotifier;
import com.google.common.collect.Lists;
import org.aspectj.lang.ProceedingJoinPoint;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith({MockitoExtension.class, SoftAssertionsExtension.class})
class NotificationAspectTest {

    @Mock
    private TelegramNotifier telegramBotNotification;

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
        verify(telegramBotNotification).sendNotification(messageCaptor.capture());

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
        verify(telegramBotNotification).sendNotification(messageCaptor.capture());

        NotificationMessage actualMessage = messageCaptor.getValue();

        softly.assertThat(actualMessage.getDescription()).isEqualTo("Description");
        softly.assertThat(actualMessage.getMethodName()).isEqualTo("Name");
        softly.assertThat(actualMessage.getExceptionName()).isEqualTo("Error, input should contain number>0");
    }

}