// package com.scd;

// import static org.junit.Assert.*;

// import java.time.LocalDateTime;
// import java.util.List;

// import org.junit.After;
// import org.junit.Before;
// import org.junit.Test;

// import com.scd.Data.OrdersDAO;
// import com.scd.Helper.FactoryProvider;
// import com.scd.Models.Customer;
// import com.scd.Models.Item;
// import com.scd.Models.Orders;
// import com.scd.Models.Product;

// public class OrdersDAOTest {

//     private OrdersDAO ordersDAO;

//     @Before
//     public void setUp() throws Exception {
//         ordersDAO = new OrdersDAO();
//     }

//     @After
//     public void tearDown() throws Exception {
//         FactoryProvider.close();
//     }

//     @Test
//     public void testSave() {
//         Orders order = new Orders();
//         Customer customer = new Customer();
//         customer.setName("ABCD");
//         order.setCustomer(customer);
//         order.setDate(LocalDateTime.now());
//         order.setStatus("Pending");
//         order.setTotal(100.0);

//         Item item = new Item();
//         Product product = new Product();
//         product.setName("TestProduct");
//         product.setDescription("TestDescription");
//         product.setPrice(50.0);
//         product.setStockQuantity(100);
//         product.setExpirationDate(LocalDateTime.now().plusDays(30));
//         item.setProduct(product);
//         item.setPrice(50.0);
//         item.setQuantityOrdered(2);
//         order.addItem(item);

//         assertTrue(ordersDAO.save(order));
//     }


//     @Test
//     public void testGetById() {
//         Orders order = new Orders();
//         Customer customer = new Customer();
//         customer.setName("John Doe");
//         order.setCustomer(customer);
//         order.setDate(LocalDateTime.now());
//         order.setStatus("Pending");
//         order.setTotal(100.0);

//         Item item = new Item();
//         Product product = new Product();
//         product.setName("TestProduct");
//         product.setDescription("TestDescription");
//         product.setPrice(50.0);
//         product.setStockQuantity(100);
//         product.setExpirationDate(LocalDateTime.now().plusDays(30));
//         item.setProduct(product);
//         item.setPrice(50.0);
//         item.setQuantityOrdered(2);
//         order.addItem(item);

//         ordersDAO.save(order);

//         Orders savedOrder = ordersDAO.getById(order.getId());
//         assertNotNull(savedOrder);
//         assertEquals(order.getId(), savedOrder.getId());
//     }

//     @Test
//     public void testUpdate() {
//         Orders order = new Orders();
//         Customer customer = new Customer();
//         customer.setName("John Doe");
//         order.setCustomer(customer);
//         order.setDate(LocalDateTime.now());
//         order.setStatus("Pending");
//         order.setTotal(100.0);

//         Item item = new Item();
//         Product product = new Product();
//         product.setName("TestProduct");
//         product.setDescription("TestDescription");
//         product.setPrice(50.0);
//         product.setStockQuantity(100);
//         product.setExpirationDate(LocalDateTime.now().plusDays(30));
//         item.setProduct(product);
//         item.setPrice(50.0);
//         item.setQuantityOrdered(2);
//         order.addItem(item);

//         ordersDAO.save(order);
//         order.setStatus("UpdatedStatus");
//         assertTrue(ordersDAO.update(order));
//         Orders updatedOrder = ordersDAO.getById(order.getId());
//         assertNotNull(updatedOrder);
//         assertEquals("UpdatedStatus", updatedOrder.getStatus());
//     }

//     @Test
//     public void testDelete() {
//         Orders order = new Orders();
//         Customer customer = new Customer();
//         customer.setName("John Doe");
//         order.setCustomer(customer);
//         order.setDate(LocalDateTime.now());
//         order.setStatus("Pending");
//         order.setTotal(100.0);

//         Item item = new Item();
//         Product product = new Product();
//         product.setName("TestProduct");
//         product.setDescription("TestDescription");
//         product.setPrice(50.0);
//         product.setStockQuantity(100);
//         product.setExpirationDate(LocalDateTime.now().plusDays(30));
//         item.setProduct(product);
//         item.setPrice(50.0);
//         item.setQuantityOrdered(2);
//         order.addItem(item);

//         ordersDAO.save(order);
//         assertTrue(ordersDAO.delete(order.getId()));

//         Orders deletedOrder = ordersDAO.getById(order.getId());
//         assertNull(deletedOrder);
//     }
// }
