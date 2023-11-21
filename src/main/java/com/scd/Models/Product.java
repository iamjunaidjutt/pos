package com.scd.Models;

import java.util.Locale.Category;
import java.util.List;

public class Product {
    private String code;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;
    private List<Category> categories;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Product [code=" + code + ", name=" + name + ", description=" + description + ", price=" + price
                + ", stockQuantity=" + stockQuantity + ", categories=" + categories + "]";
    }

}