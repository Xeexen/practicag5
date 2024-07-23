package com.bezkoder.spring.thymeleaf.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(@Param("keyword") String keyword) {
        return "login";
    }

}