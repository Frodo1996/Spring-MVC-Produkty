package com.example.zadanie_produkty;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private final List<Product> products;

    public ProductRepository() {
        products = new ArrayList<>();
        products.add(new Product("Spodnie jeansowe", 199.99, Category.OTHER));
        products.add(new Product("T-Shirt", 69.99, Category.OTHER));
        products.add(new Product("Ręcznik papierowy", 4.99, Category.HOUSEHOLD));
        products.add(new Product("Gąbki do mycia naczyń", 12.99, Category.HOUSEHOLD));
        products.add(new Product("Miotła", 9.99, Category.HOUSEHOLD));
        products.add(new Product("Szynka z indyka 101%", 29.99, Category.FOODSTUFFS));
        products.add(new Product("Bułki pełnoziarniste", 13.75, Category.FOODSTUFFS));
    }

    List<Product> allProducts() {
        return products;
    }

    void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> findByCategory(Category category) {
        List<Product> productsByGroceryCategory = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().equals(category)) {
                productsByGroceryCategory.add(product);
            }
        }
        return productsByGroceryCategory;
    }
}