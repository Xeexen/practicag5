package com.bezkoder.spring.thymeleaf.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class CustomersDataSourceProperties {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.customers")
    public DataSourceProperties customerssDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource customersDataSource() {
        return customerssDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

}
