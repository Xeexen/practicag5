package com.bezkoder.spring.thymeleaf.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ErrorNotificationService {

    @Autowired
    private JavaMailSender mailSender;

    @KafkaListener(topics = "error-topic", groupId = "myGroup")
    public void listenForErrors(String errorMessage) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("masapantaandres@gmail.com.com");
        mailMessage.setSubject("Error Notification");
        mailMessage.setText("An error occurred: " + errorMessage);
        mailSender.send(mailMessage);
    }
}

