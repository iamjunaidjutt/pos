package com.scd;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.scd.Business.InventoryManagement;
import com.scd.Models.Product;

public class InventoryManagementTest {

    private InventoryManagement inventoryManagement;
    private Product testProduct;

    @Before
    public void setUp() {
        inventoryManagement = new InventoryManagement(10); 
        testProduct = new Product();
        testProduct.setName("TestProduct");
        testProduct.setDescription("Test Description");
        testProduct.setPrice(50);
        testProduct.setStockQuantity(15);
    }

    // @Test
    // public void testUpdateProductQuantityAfterSale() {
    //     // Initial stock is 15, selling 5 items
    //     inventoryManagement.updateProductQuantityAfterSale(testProduct, 5);
    //     assertEquals(10, testProduct.getStockQuantity());
    // }

    @Test
    public void testSetThreshold() {
        inventoryManagement.setthreshold(5);
        assertEquals(5, inventoryManagement.getthreshold());
    }

    @Test
    public void testGetThreshold() {
        assertEquals(10, inventoryManagement.getthreshold());
    }

    @Test
    public void testReplenishInventory() {
        inventoryManagement.replenishInventory(testProduct, 20);
        assertEquals(35, testProduct.getStockQuantity());
    }

    @Test
    public void testTrackExpirationDates() {
        assertTrue(true); // Placeholder assertion
    }

    @Test
    public void testGetAllProducts() {
        assertTrue(inventoryManagement.getAllProducts().isEmpty());
    }
}

