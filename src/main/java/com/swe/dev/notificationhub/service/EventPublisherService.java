package com.swe.dev.notificationhub.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventPublisherService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public EventPublisherService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishEvent(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
}
