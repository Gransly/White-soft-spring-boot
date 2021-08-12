package com.example.calculatorspring.service.notification;

import com.example.calculatorspring.entity.DailySummaryMessage;
import com.example.calculatorspring.entity.NotificationMessage;
import org.springframework.scheduling.annotation.Async;

public interface Notification {

    @Async
    void sendNotification(NotificationMessage messageArg);

    @Async
    void sendDaily(DailySummaryMessage messageArg);
}
