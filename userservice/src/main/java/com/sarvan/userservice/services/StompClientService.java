package com.sarvan.userservice.services;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@Service
@Slf4j
@Profile("!test")
public class StompClientService {

    private final WebSocketStompClient stompClient;
    private volatile StompSession session;
    private final String wsUrl;

    public StompClientService(
            @Value("${app.ws.url}") String wsUrl) {

        this.wsUrl = wsUrl;
        this.stompClient =
                new WebSocketStompClient(new StandardWebSocketClient());

        this.stompClient.setMessageConverter(
                new MappingJackson2MessageConverter());
    }

    @PostConstruct
    public void connect() {
        reconnect();
    }

    private synchronized void reconnect() {
        try {
            log.info("Connecting to STOMP server: {}", wsUrl);
            this.session = stompClient
                    .connect(wsUrl, new SessionHandler())
                    .get();
            log.info("STOMP connected: {}", session.getSessionId());
        } catch (Exception e) {
            log.error("STOMP connection failed", e);
        }
    }

    public void send(String destination, Object payload) {
        if (session == null || !session.isConnected()) {
            log.warn("STOMP session not connected, reconnecting...");
            reconnect();
        }
        session.send(destination, payload);
    }

    private class SessionHandler extends StompSessionHandlerAdapter {

        @Override
        public void afterConnected(
                StompSession session, StompHeaders headers) {
            log.info("STOMP session established");
        }

        @Override
        public void handleTransportError(
                StompSession session, Throwable exception) {
            log.error("Transport error", exception);
            reconnect();
        }
    }
}
