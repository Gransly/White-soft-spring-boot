package com.example.calculatorspring.service.notification;

import com.example.calculatorspring.config.WebClientConfiguration;
import com.example.calculatorspring.entity.NotificationMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
public class TelegramBotNotification implements Notification {


    public final String TELEGRAM_BOT_TOKEN;
    public final String TELEGRAM_BOT_CHAT_ID;
    private final WebClient webClient;


    public TelegramBotNotification(Environment environment, WebClientConfiguration webClientBuilder) {
        TELEGRAM_BOT_TOKEN = environment.getRequiredProperty("telegram.bot-token");
        TELEGRAM_BOT_CHAT_ID = environment.getRequiredProperty("telegram.bot-id");
        webClient = webClientBuilder.myWebClient();
    }


    @Override
    public void sendNotification(NotificationMessage messageArg) {

        String message = "Call time: " +
                         messageArg.getCallTime() +
                         "\nMethod name: " +
                         messageArg.getMethodName()+
                         "\nMethod name: " +
                         messageArg.getDescription()+
                         "\nSuccessful: ";

        if (messageArg.getExceptionName().isEmpty()) {
            message += "Yes";
        } else {
            message += "No\n"+
                       "Cause:"+
                       messageArg.getExceptionName();
        }
        sendMessageToBot(message);

    }

    public void sendMessageToBot(String message) {

        UriComponents uri = UriComponentsBuilder.newInstance()
                                                .scheme("https")
                                                .host("api.telegram.org")
                                                .path("/bot{token}/sendMessage")
                                                .queryParam("chat_id", TELEGRAM_BOT_CHAT_ID)
                                                .queryParam("text", message)
                                                .buildAndExpand(TELEGRAM_BOT_TOKEN);

        webClient.post().uri(uri.toUri()).retrieve().bodyToMono(Void.class).block();
    }
}
