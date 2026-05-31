package com.sarvan.userservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.concurrent.TimeUnit;

@Configuration
@Profile("!test")
@Slf4j
public class WebSocketClient {

    @Value("${app.ws.url}")
    private String wsUrl;

    @Bean
    public StompSession stompSession() throws Exception {
        WebSocketStompClient client =
                new WebSocketStompClient(new StandardWebSocketClient());

        client.setMessageConverter(new MappingJackson2MessageConverter());

        log.info("Connecting to WebSocket server at: " + wsUrl);
        StompSession session = client
                .connectAsync(wsUrl, new StompSessionHandlerAdapter() {})
                .get(5, TimeUnit.SECONDS);
        log.info("WebSocket connected: " + session.isConnected());
        log.info("WebSocket id: " + session.getSessionId());
        return session;
    }
}
