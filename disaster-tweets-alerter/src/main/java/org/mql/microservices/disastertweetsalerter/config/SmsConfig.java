package org.mql.microservices.disastertweetsalerter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vonage.client.VonageClient;

@Configuration
public class SmsConfig {
    @Value("${sms.sender.key}")
    private String API_KEY;
    @Value("${sms.sender.secret}")
    private String API_SECRET;
    
    @Bean
    public VonageClient smsSenderClient() {
        VonageClient client = VonageClient.builder()
        .apiKey(API_KEY)
        .apiSecret(API_SECRET)
        .build();
        return client;
    }
}
