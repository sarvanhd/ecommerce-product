package com.sarvan.userservice.servicesImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sarvan.userservice.entities.Users;
import com.sarvan.userservice.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaService<T> {
    private final KafkaTemplate<String, T> kafkaTemplate;
    private final UserService userService;
    private final ObjectMapper objectMapper;

    public KafkaService(KafkaTemplate<String, T> kafkaTemplate, UserService userService, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    public void sendMessage(String topic, T message) {
        kafkaTemplate.send(topic, message);
        log.info("Sent message: " + message);
    }

    @KafkaListener(topics = "user", groupId = "my-app")
    public void listen(String message) {
        try {
            Users user = objectMapper.readValue(message, Users.class);
            log.info("User: " + user);
        } catch (Exception e) {

        }
        log.info("Received message: " + message);

    }
}
