package com.example.calculatorspring.scheduler;

import com.example.calculatorspring.entity.DailySummaryMessage;
import com.example.calculatorspring.service.LogService;
import com.example.calculatorspring.service.notification.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
public class DailyScheduler {

    private final LogService logService;

    private final List<Notification> notifiers;

    @Scheduled(cron = "0 2 * * * *")
    public void sendDailyInfo() {
        LocalDateTime dateTime = LocalDateTime.now();

        DailySummaryMessage message = DailySummaryMessage.builder()
                                                         .callTime(dateTime)
                                                         .requestCount(logService.getNumberOfRequest())
                                                         .minInput(logService.getMinInput())
                                                         .maxInput(logService.getMaxInput()).build();

        for (Notification notification: notifiers)
        {
          notification.sendDaily(message);
        }
    }
}