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

    @BeforeClass
    public static void setUp() {
        itemDAO = new ItemDAO();
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
        
        product.setName("TestProduct tp");
        product.setDescription("Product description tp");
        product.setPrice(10);
        product.setStockQuantity(5);

        assertTrue(new ProductDAO().save(product));

        item.setProduct(product);
        item.setPrice(150);
        item.setQuantityOrdered(4);

        assertTrue(itemDAO.save(item));
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

        product.setName("TestProduct01");
        product.setDescription("Product description pr01");
        product.setPrice(70);
        product.setStockQuantity(15);

        assertTrue(new ProductDAO().save(product));

        item.setProduct(product);
        item.setPrice(50);
        item.setQuantityOrdered(2);
        itemDAO.save(item);

        item.setPrice(100);
        assertTrue(itemDAO.update(item));
    }

    @Test
    public void testDelete() {
        Item item = new Item();
        Product product = new Product();

        product.setName("TestProduct0");
        product.setDescription("Product description test random");
        product.setPrice(50);
        product.setStockQuantity(10);

        assertTrue(new ProductDAO().save(product));

        item.setProduct(product);
        item.setPrice(80);
        item.setQuantityOrdered(5);

        itemDAO.save(item);

        assertTrue(itemDAO.delete(item.getCode()));
    }

    @Test
    public void testGetById() {
        Item item = new Item();
        Product product = new Product();

        product.setName("TestProduct1");
        product.setDescription("Product description test1");
        product.setPrice(40);
        product.setStockQuantity(10);

        assertTrue(new ProductDAO().save(product));

        item.setProduct(product);
        item.setPrice(90);
        item.setQuantityOrdered(5);

        itemDAO.save(item);

        Item retrievedItem = itemDAO.getById(item.getCode());
        assertNotNull(retrievedItem);
        assertEquals(item.getCode(), retrievedItem.getCode());
    }

    @Test
    public void testSaveItemWithProduct() {
        Item item = new Item();
        Product product = new Product();

        product.setName("TestProduct2");
        product.setDescription("Product test description");
        product.setPrice(500);
        product.setStockQuantity(10);

        assertTrue(new ProductDAO().save(product));
        assertTrue(itemDAO.saveItemWithProduct(item, product));
    }
}

