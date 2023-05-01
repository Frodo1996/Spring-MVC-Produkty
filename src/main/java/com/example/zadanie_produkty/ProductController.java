package com.example.zadanie_produkty;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

//    DecimalFormat decimalFormat = new DecimalFormat("#.##");
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
        double sumOfPrices = 0;
        if (category != null) {
            productList = productRepository.findByCategory(category);
            sumOfPrices = calculateSumOfPrices(productList);
        } else {
            productList = productRepository.allProducts();
            sumOfPrices = calculateSumOfPrices(productList);
//            sumOfPrices = Double.parseDouble(decimalFormat.format(sumOfPrices));
        }
        long rounded = Math.round(sumOfPrices);
        model.addAttribute("products", productList);
        model.addAttribute("sumOfPrices", rounded);
        return "list";
    }

    private static double calculateSumOfPrices(List<Product> productList) {
        double sumOfPrices = 0;
        for (Product product : productList) {
            sumOfPrices += product.getPrice();
        }
        return sumOfPrices;
    }

    @PostMapping("/add")
    String addProduct(String name, double price, Category category) {
        productRepository.addProduct(new Product(name, price, category));
        return "redirect:/list";
    }
}