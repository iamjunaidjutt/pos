package com.scd.Models;

public class CategorySales {
    private String name;
    private int quantity;
    private double total;

    public CategorySales(String name, int quantity, double total) {
        this.name = name;
        this.quantity = quantity;
        this.total = total;
    }

    public CategorySales() {
    }

    /**
     * @return String
     */
    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void setTotal(double total) {
        this.total += total;
    }

    @Override
    public String toString() {
        return "CategorySales [name=" + name + ", quantity=" + quantity + ", total=" + total + "]";
    }
}
