package com.bezkoder.spring.thymeleaf.services;

import com.bezkoder.spring.thymeleaf.repository.customers.CustomerRepository;
import com.bezkoder.spring.thymeleaf.repository.tutorials.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private TutorialRepository tutorialRepository;
}
