package com.bezkoder.spring.thymeleaf.services;

import com.bezkoder.spring.thymeleaf.repository.customers.CustomerRepository;
import com.bezkoder.spring.thymeleaf.repository.employees.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
}
