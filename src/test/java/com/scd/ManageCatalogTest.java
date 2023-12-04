package com.scd;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.scd.Business.ManageCatalog;
import com.scd.Data.CategoryDAO;
import com.scd.Data.ProductDAO;
import com.scd.Models.Category;
import com.scd.Models.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ManageCatalogTest {

    private ManageCatalog manageCatalog;
    private CategoryDAO categoryDAO;
    private ProductDAO productDAO;

    @Before
    public void setUp() {
        manageCatalog = new ManageCatalog();
        categoryDAO = new CategoryDAO();
        productDAO = new ProductDAO();
    }

    @AfterClass
    public static void tearDown() {
        // You may add cleanup logic here if needed

    }

    @Test
    public void testGetAllCategories() {
        assertNotNull(manageCatalog.getAllCategories());
    }

    @Test
    public void testGetAllSubCategories() {
        Category parentCategory = new Category();

        parentCategory.setName("ParentCategory");
        parentCategory.setDescription("Parent Description");

        Category subCategory = new Category();

        subCategory.setName("SubCategory");
        subCategory.setDescription("Sub Description");

        categoryDAO.save(parentCategory);
        categoryDAO.save(subCategory);

        manageCatalog.addSubCategory(parentCategory.getCode(), subCategory);

        assertNotNull(manageCatalog.getAllSubCategories(parentCategory));

        categoryDAO.delete(parentCategory.getCode());
        categoryDAO.delete(subCategory.getCode());

        manageCatalog.deleteCategory(parentCategory.getCode());
        manageCatalog.deleteSubCategory(parentCategory, subCategory);
    }

    @Test
    public void testGetCategory() {
        Category category = new Category();
        category.setName("TestCategory new1");
        category.setDescription("Test Description new1");

        categoryDAO.save(category);

        assertNotNull(manageCatalog.getCategory(category.getCode()));
        manageCatalog.deleteCategory(category.getCode());
    }

    @Test
    public void testUpdateCategory() {
        Category category = new Category();
        category.setName("TestCategory001");
        category.setDescription("Test Description001");

        categoryDAO.save(category);

        assertTrue(manageCatalog.updateCategory(category.getCode(), "UpdatedName", "UpdatedDescription"));
        categoryDAO.delete(category.getCode());
    }

    @Test
    public void testDeleteCategory() {
        Category category = new Category();
        category.setName("TestCategory1");
        category.setDescription("Test Description111");

        categoryDAO.save(category);

        assertTrue(manageCatalog.deleteCategory(category.getCode()));
    }

    @Test
    public void testDeleteSubCategory() {
        Category parentCategory = new Category();
        parentCategory.setName("ParentCategory1024");
        parentCategory.setDescription("Parent Description1024");

        Category subCategory = new Category();
        subCategory.setName("SubCategory1024");
        subCategory.setDescription("Sub Description1024");

        assertTrue(categoryDAO.save(parentCategory));
        assertTrue(categoryDAO.save(subCategory));

        assertTrue(manageCatalog.addSubCategory(parentCategory.getCode(), subCategory));
        assertTrue(manageCatalog.deleteSubCategory(parentCategory, subCategory));

        assertTrue(categoryDAO.delete(parentCategory.getCode()));
        assertTrue(categoryDAO.delete(subCategory.getCode()));
    }

    @Test
    public void testGetAllProducts() {
        assertNotNull(manageCatalog.getAllProducts());
    }

    @Test
    public void testGetProduct() {
        Product product = new Product();

        product.setName("Pr1");
        product.setDescription("Pr description...");
        product.setPrice(200);
        product.setStockQuantity(5);

        productDAO.save(product);
        assertNotNull(manageCatalog.getProduct(product.getCode()));

        productDAO.delete(product.getCode());
    }
}
