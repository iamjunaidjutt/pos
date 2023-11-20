package com.scd;

import java.util.Objects;

public class Product {
    private String code;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;

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

    public double getCurrentPrice() {
        return price;
    }

    public Item createItem(int quantityOrdered) {
        return new Item(this, quantityOrdered);
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
}

