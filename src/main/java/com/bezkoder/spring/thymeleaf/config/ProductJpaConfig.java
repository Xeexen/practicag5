package com.bezkoder.spring.thymeleaf.config;

import com.bezkoder.spring.thymeleaf.entity.Products;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackageClasses = Products.class,
        entityManagerFactoryRef = "productsEntityManagerFactory",
        transactionManagerRef = "productsTransactionManager",
        basePackages = "com.bezkoder.spring.thymeleaf.repository.tutorials"
)
public class ProductJpaConfig {
    @Bean
    public LocalContainerEntityManagerFactoryBean productsEntityManagerFactory(
            @Qualifier("productsDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages(Products.class)
                .properties(new HashMap<>(){{
                    put("hibernate.hbm2ddl.auto", "update");
                    put("hibernate.ddl-auto", "update");
                    put("hibernate.enable_lazy_load_no_trans", "true");
                }})
                .build();
    }

    @Bean
    public PlatformTransactionManager productsTransactionManager(
            @Qualifier("productsEntityManagerFactory") LocalContainerEntityManagerFactoryBean productsEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(productsEntityManagerFactory.getObject()));
    }
}
