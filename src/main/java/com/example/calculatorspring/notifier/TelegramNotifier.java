package com.example.calculatorspring.notifier;

import com.example.calculatorspring.entity.DailySummaryMessage;
import com.example.calculatorspring.entity.NotificationMessage;
import com.example.calculatorspring.service.notification.Notification;
import com.example.calculatorspring.service.notification.NotificationServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelegramNotifier implements Notification {

    @Autowired
    private NotificationServiceClient client;

    @Override
    public void sendNotification(NotificationMessage messageArg) {
        client.sendNotification(messageArg);
    }

    @Override
    public void sendDaily(DailySummaryMessage summaryArg) {
        client.sendSummary(summaryArg);
    }


}
