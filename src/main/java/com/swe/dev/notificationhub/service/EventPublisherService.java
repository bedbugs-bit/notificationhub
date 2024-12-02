package com.swe.dev.notificationhub.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventPublisherService {

    private static KafkaTemplate<String, String> kafkaTemplate = null;

    public EventPublisherService(KafkaTemplate<String, String> kafkaTemplate) {
        EventPublisherService.kafkaTemplate = kafkaTemplate;
    }

    public static void publishEvent(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
}
