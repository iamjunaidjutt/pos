// package com.scd;


// import static org.junit.Assert.*;

// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.List;

// import org.junit.After;
// import org.junit.Before;
// import org.junit.Test;

// import com.scd.Data.CartDAO;
// import com.scd.Helper.FactoryProvider;
// import com.scd.Models.Cart;
// import com.scd.Models.Item;
// import com.scd.Models.Product;

// public class CartDAOTest {

//     private CartDAO cartDAO;

//     @Before
//     public void setUp() throws Exception {
//         cartDAO = new CartDAO();
//     }

//     @After
//     public void tearDown() throws Exception {
//         FactoryProvider.close();
//     }

//     @Test
//     public void testSave() {
//         Cart cart = new Cart();
        
//         List<Item> itemlist=new ArrayList<Item>();

//         Item item1 = new Item();
//         Product product = createTestProduct();

//         item1.setProduct(product);
//         item1.setPrice(50.0);
//         item1.setQuantityOrdered(2);
//         cart.addItem(item1);

//         Item item2 = new Item();
//         Product product2 = createTestProduct();

//         item2.setProduct(product2);
//         item2.setPrice(50.0);
//         item2.setQuantityOrdered(2);
//         cart.addItem(item2);

//         itemlist.add(item1);
//         itemlist.add(item2);

//         cart.setItems(itemlist);
//         cart.setId(15);
//         assertTrue(cartDAO.save(cart));
//     }


//     @Test
//     public void testGetById() {
//         Cart cart = new Cart();

//         Item item = new Item();
//         Product product = createTestProduct();
//         item.setProduct(product);
//         item.setPrice(50.0);
//         item.setQuantityOrdered(2);
//         cart.addItem(item);

//         // Save a cart first
//         cartDAO.save(cart);

//         Cart savedCart = cartDAO.getById(cart.getId());
//         assertNotNull(savedCart);
//         assertEquals(cart.getId(), savedCart.getId());
//     }

//     @Test
//     public void testUpdate() {
//         Cart cart = new Cart();

//         Item item = new Item();
//         Product product = createTestProduct();
//         item.setProduct(product);
//         item.setPrice(50.0);
//         item.setQuantityOrdered(2);
//         cart.addItem(item);

//         // Save a cart first
//         cartDAO.save(cart);

//         // Update the cart and check if it's successful
//         assertTrue(cartDAO.update(cart));

//         // Fetch the updated cart and check if it's not null
//         Cart updatedCart = cartDAO.getById(cart.getId());
//         assertNotNull(updatedCart);
//     }

//     @Test
//     public void testDelete() {
//         Cart cart = new Cart();

//         Item item = new Item();
//         Product product = createTestProduct();
//         item.setProduct(product);
//         item.setPrice(50.0);
//         item.setQuantityOrdered(2);
//         cart.addItem(item);

//         // Save a cart first
//         cartDAO.save(cart);

//         // Deleting the cart now and check if it's successful
//         assertTrue(cartDAO.delete(cart.getId()));

//         Cart deletedCart = cartDAO.getById(cart.getId());
//         assertNull(deletedCart);
//     }

//     private Product createTestProduct() {
//         Product product = new Product();
//         product.setName("TestProduct");
//         product.setDescription("TestDescription");
//         product.setPrice(50.0);
//         product.setStockQuantity(100);
//         product.setExpirationDate(LocalDateTime.now().plusDays(30));
//         return product;
//     }
// }
