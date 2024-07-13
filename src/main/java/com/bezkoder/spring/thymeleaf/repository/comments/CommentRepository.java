package com.bezkoder.spring.thymeleaf.repository.comments;

import com.bezkoder.spring.thymeleaf.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    List<Comment> findByProductId(String productId);
}
