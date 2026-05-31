package com.sarvan.authservice.servicesImpl;

import com.sarvan.authservice.entities.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaService<T> {
    private final KafkaTemplate<String, T> kafkaTemplate;

    public KafkaService(KafkaTemplate<String, T> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, T message) {
        kafkaTemplate.send(topic, message);
        log.info("Sent message: " + message);
    }

    @KafkaListener(topics = "auth", groupId = "my-app")
    public void listen(String message) {
        log.info("Received message: " + message);

    }
}
