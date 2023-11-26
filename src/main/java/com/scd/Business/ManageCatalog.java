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

    public boolean addCategory(String name, String description) {
        CategoryDAO categoryDAO = new CategoryDAO();
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        return categoryDAO.save(category);
    }

    public boolean addSubCategory(Category category, Category subCategory) {
        CategoryDAO categoryDAO = new CategoryDAO();
        return categoryDAO.addSubCategory(category, subCategory);
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

    public List<Category> getAllSubCategories(Category category) {
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> subCategories = categoryDAO.getSubCategories(category);
        return subCategories;
    }

    public Category getCategory(int id) {
        CategoryDAO categoryDAO = new CategoryDAO();
        Category category = new Category();
        category = categoryDAO.getById(id);
        return category;
    }

    public boolean updateCategory(int id, String name, String description) {
        CategoryDAO categoryDAO = new CategoryDAO();
        Category category = new Category();
        category = categoryDAO.getById(id);
        return categoryDAO.update(category);
    }

    public boolean deleteCategory(int id) {
        CategoryDAO categoryDAO = new CategoryDAO();
        return categoryDAO.delete(id);
    }

    public boolean deleteSubCategory(Category category, Category subCategory) {
        CategoryDAO categoryDAO = new CategoryDAO();
        return categoryDAO.deleteSubCategory(category, subCategory);
    }

    public boolean addProduct(String name, String description, double price, int stockQuantity,
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
        return productDAO.update(product);
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

    public Product getProduct(int id) {
        ProductDAO productDAO = new ProductDAO();
        Product product = new Product();
        product = productDAO.getById(id);
        return product;
    }

    // public static void main(String[] args) {
    // ManageCatalog manageCatalog = new ManageCatalog();
    // // Category category = new Category();
    // // category.setName("Category 1");
    // // category.setDescription("Category 1 description");
    // // // manageCatalog.addCategory(category.getName(),
    // category.getDescription());
    // // Category category2 = new Category();
    // // category2.setName("Category 2");
    // // category2.setDescription("Category 2 description");
    // // // manageCatalog.addCategory(category2.getName(),
    // // Category category3 = new Category();
    // // category3.setName("Category 3");
    // // category3.setDescription("Category 3 description");
    // // // manageCatalog.addCategory(category3.getName(),
    // // Category category4 = new Category();
    // // category4.setName("Category 4");
    // // category4.setDescription("Category 4 description");
    // // // manageCatalog.addCategory(category4.getName(),

    // // List<Category> categories = manageCatalog.getAllCategories();

    // // for (Category category : categories) {
    // // System.out.println(category.getName());
    // // List<Category> subCategories =
    // manageCatalog.getAllSubCategories(category);
    // // for (Category subCategory : subCategories) {
    // // System.out.println(subCategory.getName());
    // // }
    // // }

    // // manageCatalog.addProduct("Product 1", "Product 1 description", 10.0, 10,
    // // LocalDateTime.now(), categories);
    // // manageCatalog.addProduct("Product 2", "Product 2 description", 20.0, 20,
    // // LocalDateTime.now(), categories);
    // // manageCatalog.addProduct("Product 3", "Product 3 description", 30.0, 30,
    // // LocalDateTime.now(), categories);
    // // manageCatalog.addProduct("Product 4", "Product 4 description", 40.0, 40,
    // // LocalDateTime.now(), categories);

    // // List<Product> products = manageCatalog.getAllProducts();
    // // for (Product product : products) {
    // // System.out.println(product.getName());
    // // }

    // // Category suCategory = new Category();
    // // suCategory.setName("Subcategory 1");
    // // suCategory.setDescription("Subcategory 1 description");

    // // manageCatalog.addSubCategory(categories.get(0), suCategory);

    // // Category suCategory2 = new Category();
    // // suCategory2.setName("Subcategory 2");
    // // suCategory2.setDescription("Subcategory 2 description");

    // // manageCatalog.addSubCategory(categories.get(0), suCategory2);

    // // Category suCategory3 = new Category();
    // // suCategory3.setName("Subcategory 3");
    // // suCategory3.setDescription("Subcategory 3 description");

    // // manageCatalog.addSubCategory(categories.get(1), suCategory3);

    // // Category category = manageCatalog.getCategory(1);
    // // System.out.println(category.getName());
    // // List<Category> subCategories =
    // manageCatalog.getAllSubCategories(category);
    // // for (Category subCategory : subCategories) {
    // // System.out.println(subCategory.getName());
    // // }
    // // manageCatalog.deleteSubCategory(category, subCategories.get(0));

    // }

}
