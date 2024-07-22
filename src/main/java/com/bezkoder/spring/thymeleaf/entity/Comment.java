package com.bezkoder.spring.thymeleaf.entity;

import javax.persistence.*;

@Entity
@Table(name = "Comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 128, nullable = false)
    private String email;

    @Column(length = 128, nullable = false)
    private String comment;

    @Column(length = 128, nullable = false)
    private String productId;

    public Comment() {
    }

    private Comment(Integer id, String email, String comment, String productId) {
        this.id = id;
        this.email = email;
        this.comment = comment;
        this.productId = productId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
