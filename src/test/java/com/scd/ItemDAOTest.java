package com.scd;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import com.scd.Data.ItemDAO;
import com.scd.Data.ProductDAO;
import com.scd.Models.Item;
import com.scd.Models.Product;

public class ItemDAOTest {

    private static ItemDAO itemDAO;
    private static ProductDAO productDAO;

    @BeforeClass
    public static void setUp() {
        itemDAO = new ItemDAO();
        productDAO = new ProductDAO();
    }

    @AfterClass
    public static void tearDown() {
        List<Object> items = itemDAO.getAll();
        for (Object item : items) {
            itemDAO.delete(((Item) item).getCode());
        }
    }

    @Test
    public void testSave() {
        Item item = new Item();
        Product product = new Product();
        product.setName("TestProduct tp1");
        product.setDescription("Product description tp1");
        product.setPrice(150);
        product.setStockQuantity(5);

        assertTrue(productDAO.save(product));

        item.setProduct(product);
        item.setPrice(150);
        item.setQuantityOrdered(4);

        assertTrue(itemDAO.save(item));

        productDAO.delete(product.getCode());
        itemDAO.delete(item.getCode());
    }

    @Test
    public void testGetAll() {
        List<Object> items = itemDAO.getAll();
        assertNotNull(items);
    }

    @Test
    public void testUpdate() {
        Item item = new Item();
        Product product = new Product();
        product.setName("TestProduct tp2");
        product.setDescription("Product description tp2");
        product.setPrice(70);
        product.setStockQuantity(15);

        assertTrue(productDAO.save(product));

        item.setProduct(product);
        item.setPrice(50);
        item.setQuantityOrdered(2);
        itemDAO.save(item);

        item.setPrice(100);
        assertTrue(itemDAO.update(item));

        productDAO.delete(product.getCode());
        itemDAO.delete(item.getCode());
    }

    @Test
    public void testDelete() {
        Item item = new Item();
        Product product = new Product();
        product.setName("TestProduct tp3");
        product.setDescription("Product description tp3");
        product.setPrice(50);
        product.setStockQuantity(10);

        assertTrue(productDAO.save(product));

        item.setProduct(product);
        item.setPrice(80);
        item.setQuantityOrdered(5);

        itemDAO.save(item);

        assertTrue(itemDAO.delete(item.getCode()));

        productDAO.delete(product.getCode());
        itemDAO.delete(item.getCode());
    }

    @Test
    public void testGetById() {
        Item item = new Item();
        Product product = new Product();
        product.setName("TestProduct tp4");
        product.setDescription("Product description tp4");
        product.setPrice(40);
        product.setStockQuantity(10);

        assertTrue(productDAO.save(product));

        item.setProduct(product);
        item.setPrice(90);
        item.setQuantityOrdered(5);

        itemDAO.save(item);

        Item retrievedItem = itemDAO.getById(item.getCode());
        assertNotNull(retrievedItem);
        assertEquals(item.getCode(), retrievedItem.getCode());

        productDAO.delete(product.getCode());
        itemDAO.delete(item.getCode());
    }

    @Test
    public void testSaveItemWithProduct() {
        Item item = new Item();
        Product product = new Product();
        product.setName("TestProduct tp5");
        product.setDescription("Product description tp5");
        product.setPrice(500);
        product.setStockQuantity(10);

        assertTrue(productDAO.save(product));
        assertTrue(itemDAO.saveItemWithProduct(item, product));

        productDAO.delete(product.getCode());
        itemDAO.delete(item.getCode());
    }
}
