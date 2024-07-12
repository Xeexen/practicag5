package com.bezkoder.spring.thymeleaf.entity;

import javax.persistence.*;

@Entity
@Table(name = "Comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 128, nullable = false)
    private String comment;
    // Getters and setters

    protected Comment() {
    }

    public Comment(String comment) {
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
