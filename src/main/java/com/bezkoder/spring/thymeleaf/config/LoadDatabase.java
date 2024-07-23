package com.bezkoder.spring.thymeleaf.config;

import com.bezkoder.spring.thymeleaf.entity.Employee;
import com.bezkoder.spring.thymeleaf.repository.employees.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class LoadDatabase {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    @Lazy
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            if (employeeRepository.findByUsername("admin") == null) {
                Employee admin = new Employee();
                admin.setUsername("admin");
                admin.setPassword(bCryptPasswordEncoder.encode("admin"));
                admin.setFirstName("Admin");
                admin.setLastName("Admin");
                admin.setDepartment("Administration");
                employeeRepository.save(admin);
            }
        };
    }
}