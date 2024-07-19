package com.bezkoder.spring.thymeleaf.controller;

import java.util.ArrayList;
import java.util.List;

import com.bezkoder.spring.thymeleaf.entity.Comment;
import com.bezkoder.spring.thymeleaf.repository.comments.CommentRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bezkoder.spring.thymeleaf.entity.Products;
import com.bezkoder.spring.thymeleaf.repository.tutorials.TutorialRepository;

@Controller
public class TutorialController {


    private final TutorialRepository tutorialRepository;

    private final CommentRepository commentRepository;

    public TutorialController(TutorialRepository tutorialRepository, CommentRepository commentRepository) {
        this.tutorialRepository = tutorialRepository;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/products")
    public String getAll(Model model, @Param("keyword") String keyword) {
        try {
            List<Products> tutorials = new ArrayList<Products>();

            if (keyword == null) {
                tutorialRepository.findAll().forEach(tutorials::add);
            } else {
                tutorialRepository.findByTitleContainingIgnoreCase(keyword).forEach(tutorials::add);
                model.addAttribute("keyword", keyword);
            }
            System.out.println(tutorials.size());
            model.addAttribute("tutorials", tutorials);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "products";
    }

    @GetMapping("/products/new")
    public String addTutorial(Model model) {
        Products tutorial = new Products();
        tutorial.setPublished(true);

        model.addAttribute("tutorial", tutorial);
        model.addAttribute("pageTitle", "Crear nuevo producto");

        return "tutorial_form";
    }

    @PostMapping("/products/save")
    public String saveTutorial(Products tutorial, RedirectAttributes redirectAttributes) {
        try {
            tutorialRepository.save(tutorial);
            commentRepository.save(new Comment("WASAAA"));
            redirectAttributes.addFlashAttribute("message", "El producto se creo con exito!");
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:/products";
    }

    @GetMapping("/products/{id}")
    public String editTutorial(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Products tutorial = tutorialRepository.findById(id).get();

            model.addAttribute("tutorial", tutorial);
            model.addAttribute("pageTitle", "Editar el Producto (ID: " + id + ")");

            return "tutorial_form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/products";
        }
    }

    @GetMapping("/products/delete/{id}")
    public String deleteTutorial(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            tutorialRepository.deleteById(id);

            redirectAttributes.addFlashAttribute("message", "El producto con id=" + id + " ha sido eliminado correctamente!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/products";
    }

    @GetMapping("/products/{id}/published/{status}")
    public String updateTutorialPublishedStatus(@PathVariable("id") Integer id, @PathVariable("status") boolean published,
                                                Model model, RedirectAttributes redirectAttributes) {
        try {
            tutorialRepository.updatePublishedStatus(id, published);

            String status = published ? "published" : "disabled";
            String message = "El producto id=" + id + " a sido " + status;

            redirectAttributes.addFlashAttribute("message", message);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/products";
    }

    @GetMapping("/comments/{productId}")
    public String getAll(@PathVariable String productId, Model model) {
        try {
            List<Comment> comments = commentRepository.findByProductId(productId);

            model.addAttribute("tutorials", comments);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "comments";
    }

    @GetMapping("/comments/new/{productId}")
    public String addComment(@PathVariable String productId, Model model) {
        Comment comment = new Comment();
        comment.setProductId(productId);

        model.addAttribute("comment", comment);
        model.addAttribute("pageTitle", "Crear nuevo comentario");

        return "comments_form";
    }


}
