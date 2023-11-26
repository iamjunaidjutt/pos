package com.scd.Business;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.scd.Data.CategoryDAO;
import com.scd.Data.ProductDAO;
import com.scd.Models.Category;
import com.scd.Models.Product;

public class ManageCatalog {

    public ManageCatalog() {

    }

    public void addCategory(String name, String description) {
        CategoryDAO categoryDAO = new CategoryDAO();
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        categoryDAO.save(category);
    }

    public List<Category> getAllCategories() {
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Object> categories = new ArrayList<Object>();
        categories = categoryDAO.getAll();
        List<Category> categories2 = new ArrayList<Category>();
        for (Object object : categories) {
            categories2.add((Category) object);
        }
        return categories2;
    }

    public Category getCategory(int id) {
        CategoryDAO categoryDAO = new CategoryDAO();
        Category category = new Category();
        category = categoryDAO.getById(id);
        return category;
    }

    public void updateCategory(int id, String name, String description) {
        CategoryDAO categoryDAO = new CategoryDAO();
        Category category = new Category();
        category = categoryDAO.getById(id);
        categoryDAO.update(category);
    }

    public void deleteCategory(int id) {
        CategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.delete(id);
    }

    public void addProduct(String name, String description, double price, int stockQuantity,
            LocalDateTime localDateTime,
            List<Category> categories) {
        ProductDAO productDAO = new ProductDAO();
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStockQuantity(stockQuantity);
        product.setExpirationDate(localDateTime);
        product.setCategories(new ArrayList<Category>());
        product.getCategories().addAll(categories);
        productDAO.update(product);
    }

    public List<Product> getAllProducts() {
        ProductDAO productDAO = new ProductDAO();
        List<Object> products = new ArrayList<Object>();
        products = productDAO.getAll();
        List<Product> products2 = new ArrayList<Product>();
        for (Object object : products) {
            products2.add((Product) object);
        }
        return products2;
    }

}
