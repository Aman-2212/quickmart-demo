package com.quickmart.quickmart_demo.controller;

import com.quickmart.quickmart_demo.model.Product;
import com.quickmart.quickmart_demo.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductController {

    private final ProductRepository productRepository;
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void loadData() {
        if (productRepository.count() == 0) {
            productRepository.save(new Product("Apple", "Fresh red apple", 50, 20));
            productRepository.save(new Product("Banana", "Organic banana", 30, 40));
            productRepository.save(new Product("Mango", "Sweet mango", 80, 25));
        }
    }

    @GetMapping("/products")
    public String list(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }

    @GetMapping("/products/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("product", productRepository.findById(id).orElse(null));
        return "product";
    }
}



