package com.scd;

public class Item {
    private Product product;
    private double price;
    private int quantityOrdered;

    public Item(Product product, int quantityOrdered) {
        this.product = product;
        this.price = product.getCurrentPrice();
        this.quantityOrdered = quantityOrdered;
    }

    public double total() {
        return price * quantityOrdered;
    }

}

