package com.example.calculatorspring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriTemplate;

@Configuration
public class WebClientConfiguration {

    @Bean
    public WebClient myWebClient() {
        return WebClient.builder()
                .build();
    }
}