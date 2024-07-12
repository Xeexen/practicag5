package com.bezkoder.spring.thymeleaf.repository.tutorials;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bezkoder.spring.thymeleaf.entity.Products;


public interface TutorialRepository extends JpaRepository<Products, Integer> {
  List<Products> findByTitleContainingIgnoreCase(String keyword);

  @Query("UPDATE Products t SET t.published = :published WHERE t.id = :id")
  @Modifying
  public void updatePublishedStatus(Integer id, boolean published);
}
