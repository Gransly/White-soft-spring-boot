package com.example.calculatorspring.service.notification;

import com.example.calculatorspring.entity.DailySummaryMessage;
import com.example.calculatorspring.entity.NotificationMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
public class TelegramBotNotification implements Notification {

    @Value("${telegram.bot-token}")
    String botToken;
    @Value("${telegram.bot-id}")
    String chatId;

    WebClient client = WebClient.create();

    @Override
    public void sendNotification(NotificationMessage messageArg) {

        String message = "\\\\Error notify//\n"+
                         "Call time: " +
                         messageArg.getCallTime() +
                         '\n' +
                         "Error method name: " +
                         messageArg.getMethodName();
        sendMessageToBot(message);
    }

    @Override
    public void sendDaily(DailySummaryMessage messageArg) {
        String message = "\\\\Daily summary//\n"+
                         "Call time:" +
                         messageArg.getCallTime()+
                         '\n'+
                         "Overall successful requests:"+
                         messageArg.getRequestCount()+
                         '\n'+
                         "Max input:"+
                         messageArg.getMaxInput()+
                         '\n'+
                         "Min input:"+
                         messageArg.getMinInput();
        sendMessageToBot(message);
    }


    public void sendMessageToBot(String message) {

        UriComponents uri = UriComponentsBuilder.newInstance()
                                                .scheme("https")
                                                .host("api.telegram.org")
                                                .path("/bot{token}/sendMessage")
                                                .queryParam("chat_id", chatId)
                                                .queryParam("text", message)
                                                .buildAndExpand(botToken);


        client.post().uri(uri.toUri()).retrieve().bodyToMono(Void.class).block();
    }
}
