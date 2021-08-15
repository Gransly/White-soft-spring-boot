package com.example.calculatorspring.service.notification;

import com.example.calculatorspring.entity.DailySummaryMessage;
import com.example.calculatorspring.entity.NotificationMessage;
import org.springframework.scheduling.annotation.Async;

public interface Notification {

    void sendNotification(NotificationMessage messageArg);

    void sendDaily(DailySummaryMessage messageArg);
}
