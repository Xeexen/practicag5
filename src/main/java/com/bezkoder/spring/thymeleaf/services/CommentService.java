package com.bezkoder.spring.thymeleaf.services;

import com.bezkoder.spring.thymeleaf.repository.comments.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

}
