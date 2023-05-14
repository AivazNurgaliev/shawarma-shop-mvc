package com.mvcapp.shawarma.controller;


import com.mvcapp.shawarma.model.entity.CategoryEntity;
import com.mvcapp.shawarma.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

// FIXME: 14.05.2023 delete, update, create - admin, GET - users, admins
@Controller
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category/{id}")
    public String getById(@PathVariable Integer id, Model model) {
        try {
            var category = categoryService.getCategory(id);
            System.out.println(category.getName());
            model.addAttribute("categories", category);
            return "category";
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            
        }
    }

    @GetMapping("/category/all")
    public String getAll(Model model) {
        var categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "category";
    }

    // TODO: 14.05.2023 query params
    // TODO: 14.05.2023 preauthorize
    @PostMapping("/category/all")
    @PreAuthorize("hasAuthority('admins:permission')")
    public String addCategory(@RequestParam String name) {
        try {
            categoryService.addCategory(name);
            return "redirect:/category/all";

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        
    }

    //mvc = get and post mappings
    @PostMapping("/category/delete")
    @PreAuthorize("hasAuthority('admins:permission')")
    public String deleteCategory(@RequestParam Integer id) {
        try {
            categoryService.deleteCategory(id);
            return "redirect:/category/all";

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
