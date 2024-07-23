package com.bezkoder.spring.thymeleaf.config;

import com.bezkoder.spring.thymeleaf.repository.customers.CustomerRepository;
import com.bezkoder.spring.thymeleaf.repository.employees.EmployeeRepository;
import com.bezkoder.spring.thymeleaf.repository.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.bezkoder.spring.thymeleaf.entity.User user = employeeRepository.findByUsername(username);
        if (user == null) {
            user = customerRepository.findByUsername(username);
        }
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}