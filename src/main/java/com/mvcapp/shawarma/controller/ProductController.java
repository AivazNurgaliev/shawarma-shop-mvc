package com.mvcapp.shawarma.controller;


import com.mvcapp.shawarma.model.entity.ProductEntity;
import com.mvcapp.shawarma.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String findAll(Model model) {
        List<ProductEntity> products = productService.findAll();
        model.addAttribute("products", products);
        return "products";
    }


}
