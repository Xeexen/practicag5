package com.bezkoder.spring.thymeleaf.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class ProductsDataSourceProperties {

    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSourceProperties productssDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource productsDataSource() {
        return productssDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }
}
