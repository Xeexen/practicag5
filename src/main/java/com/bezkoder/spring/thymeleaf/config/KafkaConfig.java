package com.bezkoder.spring.thymeleaf.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic productTopic() {
        return TopicBuilder.name("product-topic")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic commentTopic() {
        return TopicBuilder.name("comment-topic")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic customerTopic() {
        return TopicBuilder.name("customer-topic")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic employeeTopic() {
        return TopicBuilder.name("employee-topic")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public KafkaAdmin.NewTopics topics() {
        return new KafkaAdmin.NewTopics(
                TopicBuilder.name("products-broker")
                        .build(),
                TopicBuilder.name("comments-topic")
                        .build(),
                TopicBuilder.name("customers-topic")
                        .build(),
                TopicBuilder.name("employees-topic")
                        .build()
        );
    }
}
