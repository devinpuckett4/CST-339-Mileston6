package com.gcu.workspacecst339.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gcu.workspacecst339.business.ProductService;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) { this.productService = productService; }

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productService.listAll());
        return "products"; // looks for templates/products.html
    }
}