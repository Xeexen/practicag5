package com.bezkoder.spring.thymeleaf.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class ErrorProducerService {

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Value("${spring.second-datasource.url}")
    private String secondDatabaseUrl;

    @Value("${spring.second-datasource.username}")
    private String username;

    @Value("${spring.second-datasource.password}")
    private String password;

    private boolean isMysqlDatabaseDown = false;

    private boolean isPsgDatabaseDown = false;

    private boolean wasMysqlDatabaseDown = false;

    private boolean wasPsgDatabaseDown = false;

    @Autowired
    private WebClient webClient;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendError(String errorMessage) {
        this.kafkaTemplate.send("error-topic", errorMessage);
    }

    @Scheduled(fixedDelay = 60000)
    public void checkMysqlDatabase() {
        try (Connection connection = DriverManager.getConnection(secondDatabaseUrl, username, password)) {
            if (connection == null || connection.isClosed()) {
                if (!isMysqlDatabaseDown) {
                    kafkaTemplate.send("error-topic", "La base de datos en " + secondDatabaseUrl + " se ha detenido");
                    isMysqlDatabaseDown = true;
                }
            } else {
                if (isMysqlDatabaseDown) {
                    kafkaTemplate.send("error-topic", "La base de datos en " + secondDatabaseUrl + " esta respondiendo correctamente");
                    isMysqlDatabaseDown = false;
                }
            }
            wasMysqlDatabaseDown = isMysqlDatabaseDown;
        } catch (SQLException e) {
            if (!isMysqlDatabaseDown) {
                kafkaTemplate.send("error-topic", "La base de datos en " + secondDatabaseUrl + " se ha detenido");
                isMysqlDatabaseDown = true;
                wasMysqlDatabaseDown = isMysqlDatabaseDown;
            }
        }
    }

    @Scheduled(fixedDelay = 60000)
    public void checkPsgDatabase() {
        try (Connection connection = DriverManager.getConnection(databaseUrl, username, password)) {
            if (connection == null || connection.isClosed()) {
                if (!isPsgDatabaseDown) {
                    kafkaTemplate.send("error-topic", "La base de datos en " + databaseUrl + " se ha detenido");
                    isPsgDatabaseDown = true;
                }
            } else {
                if (isPsgDatabaseDown) {
                    kafkaTemplate.send("error-topic", "La base de datos en " + databaseUrl + " esta respondiendo correctamente");
                    isPsgDatabaseDown = false;
                }
            }
            wasPsgDatabaseDown = isPsgDatabaseDown;
        } catch (SQLException e) {
            if (!isPsgDatabaseDown) {
                kafkaTemplate.send("error-topic", "La base de datos en " + databaseUrl + " se ha detenido");
                isPsgDatabaseDown = true;
                wasPsgDatabaseDown = isPsgDatabaseDown;
            }
        }
    }
}
