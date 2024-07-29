package com.bezkoder.spring.thymeleaf.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ActivityLogger {

    private static final Logger logger = LoggerFactory.getLogger(ActivityLogger.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void logInfo(String message) {
        logger.info(message);
        this.kafkaTemplate.send("error-topic", message);
    }

    public void logError(String message, Exception e) {
        logger.error(message, e);
        this.kafkaTemplate.send("error-topic", message + " " + e);

    }
}
