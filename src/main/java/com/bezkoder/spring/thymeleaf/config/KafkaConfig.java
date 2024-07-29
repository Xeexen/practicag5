package com.bezkoder.spring.thymeleaf.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableScheduling
public class KafkaConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

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
                        .partitions(3)
                        .replicas(1)
                        .build(),
                TopicBuilder.name("comments-topic")
                        .partitions(3)
                        .replicas(1)
                        .build(),
                TopicBuilder.name("customers-topic")
                        .partitions(3)
                        .replicas(1)
                        .build(),
                TopicBuilder.name("employees-topic")
                        .partitions(3)
                        .replicas(1)
                        .build()
        );
    }

}
