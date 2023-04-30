package com.example.zadanie_produkty;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    String homePage() {
        return "index";
    }

    @GetMapping("/list")
    String productsList(Model model, @RequestParam(required = false) Category category) {
        List<Product> productList;
        if (category != null) {
            productList = productRepository.findByCategory(category);
        } else {
            productList = productRepository.allProducts();
        }
        model.addAttribute("products", productList);
        return "list";
    }

    @PostMapping("/add")
    String addProduct(String name, double price, Category category) {
        productRepository.addProduct(new Product(name, price, category));
        return "redirect:/list";
    }
}