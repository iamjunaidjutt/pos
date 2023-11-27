package com.scd;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.scd.Data.CategoryDAO;
import com.scd.Models.Category;

public class CategoryDAOTest {

    private static CategoryDAO categoryDAO;
    private static List<Integer> addedCategoryCodes;

    @Before
    public void setUp() {
        categoryDAO = new CategoryDAO();
        addedCategoryCodes = new ArrayList<>();
    }

    @After
    public void tearDown() {
        // to clean after  each
        for (int categoryCode : addedCategoryCodes) {
            categoryDAO.delete(categoryCode);
        }
        addedCategoryCodes.clear();
    }

    @Test
    public void testSave() {
        Category category = new Category();
        category.setName("Test Category");

        assertTrue(categoryDAO.save(category));
        addedCategoryCodes.add(category.getCode());
    }

    @Test
    public void testGetAll() {
        List<Object> categories = categoryDAO.getAll();
        assertNotNull(categories);
    }

    @Test
    public void testUpdate() {
        Category category = new Category();
        category.setName("Test Category");

        categoryDAO.save(category);

        category.setName("Updated Category");
        assertTrue(categoryDAO.update(category));
    }

    // @Test
    // public void testDelete() {
    //     Category category = new Category();
    //     category.setName("Test Category");
    //     categoryDAO.save(category);

    //     assertTrue(categoryDAO.delete(category.getCode()));
    //     addedCategoryCodes.remove(Integer.valueOf(category.getCode()));
    // }

    @Test
    public void testGetById() {
        Category category = new Category();
        category.setName("Test Category");

        categoryDAO.save(category);

        Category retrievedCategory = categoryDAO.getById(category.getCode());
        assertNotNull(retrievedCategory);
        assertEquals("Test Category", retrievedCategory.getName());
    }
}

