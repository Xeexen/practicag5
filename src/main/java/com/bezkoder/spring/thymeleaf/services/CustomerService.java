package com.bezkoder.spring.thymeleaf.services;

import com.bezkoder.spring.thymeleaf.repository.customers.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Aquí puedes agregar los métodos que necesites para manejar las operaciones de los clientes
}

