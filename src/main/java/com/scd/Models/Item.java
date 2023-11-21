package com.scd.Models;

public class Item {
    private Product product;
    private double price;
    private int quantityOrdered;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    @Override
    public String toString() {
        return "Item [product=" + product + ", price=" + price + ", quantityOrdered=" + quantityOrdered + "]";
    }

}
