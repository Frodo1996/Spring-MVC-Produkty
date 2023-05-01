package com.example.zadanie_produkty;

public enum Category {
    FOODSTUFFS("artykuły spożywczne"), HOUSEHOLD("artykuły domowe"), OTHER("inne");

    final String categoryName;

    Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}