package com.scd;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.scd.Data.ProductDAO;
import com.scd.Models.Product;

public class ProductDAOTest {

    private static ProductDAO productDAO;
    private static List<Integer> addedProductCodes;

    @Before
    public void setUp() {
        productDAO = new ProductDAO();
        addedProductCodes = new ArrayList<>();
    }

    @After
    public void tearDown() {
        // For each,will clean here
        for (int productCode : addedProductCodes) {
            productDAO.delete(productCode);
        }
        addedProductCodes.clear();
    }

    @Test
    public void testSave() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(50.0);

        assertTrue(productDAO.save(product));
        addedProductCodes.add(product.getCode());
    }

    @Test
    public void testGetAll() {
        List<Object> products = productDAO.getAll();
        assertNotNull(products);
    }

    // @Test
    // public void testUpdate() {
    //     Product product = new Product();
    //     product.setName("Test Product");
    //     product.setPrice(150.0);

    //     productDAO.save(product);

    //     product.setPrice(250.0);
    //     assertTrue(productDAO.update(product));
    // }

    // @Test
    // public void testDelete() {
    //     Product product = new Product();
    //     product.setName("Test Product");
    //     product.setPrice(150);

    //     productDAO.save(product);

    //     assertTrue(productDAO.delete(product.getCode()));
    //     addedProductCodes.remove(Integer.valueOf(product.getCode()));
    // }

    @Test
    public void testGetById() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(100.0);

        productDAO.save(product);


        Product retrievedProduct = productDAO.getById(product.getCode());
        assertNotNull(retrievedProduct);

        assertEquals("Test Product", retrievedProduct.getName());
    }
}

