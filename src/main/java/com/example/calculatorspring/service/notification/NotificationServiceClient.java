package com.example.calculatorspring.service.notification;

import com.example.calculatorspring.entity.DailySummaryMessage;
import com.example.calculatorspring.entity.NotificationMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("notify-service")
public interface NotificationServiceClient {
    @PostMapping(value = "/message/action")
    void sendNotification(@RequestBody NotificationMessage serviceAction);

    @PostMapping(value = "/message/summary")
    void sendSummary(@RequestBody DailySummaryMessage dailySummary);
}
