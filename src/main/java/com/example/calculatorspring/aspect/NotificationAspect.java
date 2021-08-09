package com.example.calculatorspring.aspect;

import com.example.calculatorspring.service.notification.Notification;
import com.example.calculatorspring.service.notification.NotificationMessage;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Aspect
@Component
@RequiredArgsConstructor
public class NotificationAspect {

    private final List<Notification> notifiers;

    @After("execution(* com.example.calculatorspring.controller.ApiExceptionHandler.*(..))")
    public void notifyAboutRequest(JoinPoint jp){

        LocalDateTime callTime = LocalDateTime.now();
        String methodName = jp.getSignature().getName();

            NotificationMessage message = NotificationMessage.builder()
                                                             .callTime(callTime)
                                                             .methodName(methodName)
                                                             .build();

            for (Notification notification : notifiers) {
                notification.sendNotification(message);
            }
    }
}
