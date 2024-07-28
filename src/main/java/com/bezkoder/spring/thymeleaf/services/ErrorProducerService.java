package com.bezkoder.spring.thymeleaf.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ErrorProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendError(String errorMessage) {
        this.kafkaTemplate.send("error-topic", errorMessage);
    }
}
