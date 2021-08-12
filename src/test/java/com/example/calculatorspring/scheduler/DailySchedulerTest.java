package com.example.calculatorspring.scheduler;

import com.example.calculatorspring.entity.DailySummaryMessage;
import com.example.calculatorspring.service.LogService;
import com.example.calculatorspring.service.notification.Notification;
import com.example.calculatorspring.service.notification.TelegramBotNotification;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DailySchedulerTest {

    @Mock
    private LogService logService;

    @Mock
    TelegramBotNotification botNotification;

    @Captor
    private ArgumentCaptor<DailySummaryMessage> summary;

    @Test
    void sendDailyInfoTest(){
        //Arrange
        DailyScheduler scheduler = new DailyScheduler(logService, Lists.newArrayList(botNotification));

        when(logService.getMaxInput()).thenReturn(100);
        when(logService.getMinInput()).thenReturn(0);
        when(logService.getNumberOfRequest()).thenReturn((long)20);

        //Act
        scheduler.sendDailyInfo();

        //Assert
        DailySummaryMessage expected = DailySummaryMessage.builder()
                                                          .maxInput(100)
                                                          .minInput(0)
                                                          .requestCount(20)
                                                          .build();

        verify(botNotification, times(1)).sendDaily(summary.capture());

        DailySummaryMessage actual = summary.getValue();

        assertThat(actual).usingRecursiveComparison()
                          .ignoringFields("callTime")
                          .withStrictTypeChecking()
                          .isEqualTo(expected);
    }

}