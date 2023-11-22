package com.scd.Business;

import java.util.ArrayList;
import java.util.List;

import com.scd.Models.Product;

public class InventoryManagement {
    private List<Product> products;
    private int threshold;

    public InventoryManagement(int threshold) {
        this.products = new ArrayList<>();
        this.threshold = threshold;
    }

    public void updateProductQuantityAfterSale(Product product, int soldQuantity) {
        // product.updateStock(soldQuantity);
        checkLowStock(product);
    }

    public void setthreshold(int threshold) {
        this.threshold = threshold;
    }

    private void checkLowStock(Product product) {
        if (product.getStockQuantity() <= threshold) {
            System.out.println("Low stock alert for product: " + product.getName());
        }
    }

    public void replenishInventory(Product product, int quantity) {
        product.setStockQuantity(product.getStockQuantity() + quantity);
    }

    public void trackExpirationDates() {
        // TO DO
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

}
