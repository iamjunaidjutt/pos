package com.scd.Business;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.scd.Data.ProductDAO;
import com.scd.Models.Item;
import com.scd.Models.Product;

public class InventoryManagement {
    ProductDAO productDAO;
    int threshold;

    public InventoryManagement(int threshold) {
        productDAO = new ProductDAO();
        this.threshold = threshold;
    }

    public boolean updateInventory(List<Item> items) {
        try {
            for (Item item : items) {
                Product product = item.getProduct();
                product.setStockQuantity(product.getStockQuantity() - item.getQuantityOrdered());
                productDAO.update(product);
                if (checkLowStock(product)) {
                    System.out.println("Product " + product.getName() + " is low on stock. Please replenish.");
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setthreshold(int threshold) {
        this.threshold = threshold;
    }

    private boolean checkLowStock(Product product) {
        if (product.getStockQuantity() < threshold) {
            return true;
        }
        return false;
    }

    public boolean replenishInventory(Product product, int quantity) {
        try {
            product.setStockQuantity(product.getStockQuantity() + quantity);
            productDAO.update(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean trackExpirationDates() {
        try {
            List<Object> objects = productDAO.getAll();
            List<Product> products = new ArrayList<>();
            for (Object object : objects) {
                products.add((Product) object);
            }
            for (Product product : products) {
                if (product.getExpirationDate() != null) {
                    if (product.getExpirationDate().compareTo(LocalDateTime.now()) < 0) {
                        System.out.println("Product " + product.getName() + " has expired.");
                        productDAO.delete(product.getCode());
                    }
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
