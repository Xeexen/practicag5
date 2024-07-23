package com.bezkoder.spring.thymeleaf.repository.customers;

import com.bezkoder.spring.thymeleaf.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUsername(String username);
}