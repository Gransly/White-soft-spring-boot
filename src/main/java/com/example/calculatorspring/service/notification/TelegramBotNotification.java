package com.example.calculatorspring.service.notification;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;

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

        StringBuilder message = new StringBuilder()
                .append("\uD83D\uDD58 Call time: ")
                .append(messageArg.getCallTime())
                .append('\n')
                .append("Error method name: ")
                .append(messageArg.getMethodName());

        sendMessageViaBot(message.toString());
    }

    public void sendMessageViaBot(String message) {

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
