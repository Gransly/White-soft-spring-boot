package com.example.calculatorspring.aspect;

import com.example.calculatorspring.entity.NotificationMessage;
import com.example.calculatorspring.service.notification.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class NotificationAspect {

    private final List<Notification> notifiers;

    @Around(value = "@annotation(notify)")
    public Object notifyAboutRequest(ProceedingJoinPoint joinPoint, Notify notify) throws Throwable {

        LocalDateTime callTime = LocalDateTime.now();

        String methodName = joinPoint.getSignature().getName();

        String exceptionClassName = "";

        Object proceed;

        try {
            proceed = joinPoint.proceed();
        } catch (Exception e) {

            exceptionClassName =e.getMessage();
            throw e;
        } finally {
            NotificationMessage message = NotificationMessage.builder()
                                                             .callTime(callTime)
                                                             .methodName(methodName)
                                                             .description(notify.description())
                                                             .exceptionName(exceptionClassName)
                                                             .build();

            for (Notification notification : notifiers) {
                notification.sendNotification(message);
            }
        }
        return proceed;
    }
}
