package com.example.calculatorspring.service.notification;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class NotificationMessage {
    LocalDateTime callTime;
    String methodName;
}
