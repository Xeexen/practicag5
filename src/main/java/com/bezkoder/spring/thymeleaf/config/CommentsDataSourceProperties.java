package com.bezkoder.spring.thymeleaf.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class CommentsDataSourceProperties {
    @Bean
    @ConfigurationProperties(prefix="spring.second-datasource")
    public DataSourceProperties commentssDataSourceProperties() {
        return new DataSourceProperties();
    }
    @Bean
    public DataSource commentsDataSource() {
        return commentssDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }
}
