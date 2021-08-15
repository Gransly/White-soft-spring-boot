package com.example.calculatorspring.service.notification;

import com.example.calculatorspring.entity.NotificationMessage;

public interface Notification {

    void sendNotification(NotificationMessage messageArg);
}
