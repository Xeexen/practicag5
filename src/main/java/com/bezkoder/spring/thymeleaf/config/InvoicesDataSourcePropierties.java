package com.bezkoder.spring.thymeleaf.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class InvoicesDataSourcePropierties {
        @Bean
        @ConfigurationProperties(prefix = "spring.datasource.invoices")
        public DataSourceProperties invoicessDataSourceProperties() {
            return new DataSourceProperties();
        }

        @Bean
        public DataSource invoicesDataSource() {
            return invoicessDataSourceProperties()
                    .initializeDataSourceBuilder()
                    .build();
        }
}
