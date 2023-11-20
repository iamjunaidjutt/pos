package com.scd;

import java.util.Locale.Category;
import java.util.Objects;

public class Product {
    private String code;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;

    private Category category;

    public Product(String code, String name, String description, double price, int stockQuantity) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }


    public void updateStock(int soldQuantity) {
        if (soldQuantity > 0 && soldQuantity <= stockQuantity) {
            stockQuantity -= soldQuantity;
        } else {
            System.out.println("Invalid stock update for product " + name);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(code, product.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }


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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }



    public double getCurrentPrice() {
        return price;
    }

    public Item createItem(int quantityOrdered) {
        return new Item(this, quantityOrdered);
    }


}