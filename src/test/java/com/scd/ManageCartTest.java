// package com.scd;

// import static org.junit.Assert.*;

// import java.util.List;

// import org.junit.AfterClass;
// import org.junit.BeforeClass;
// import org.junit.Test;

// import com.scd.Business.ManageCart;
// import com.scd.Data.CartDAO;
// import com.scd.Models.Cart;
// import com.scd.Models.Orders;

// public class ManageCartTest {

//     private static ManageCart manageCart;

//     @BeforeClass
//     public static void setUp() {
//         // Initialize resources before running the tests
//         manageCart = new ManageCart();
//     }

//     @AfterClass
//     public static void tearDown() {
//         // Clean up resources after running the tests
//         List<Cart> carts = manageCart.getCartDetails();
//         for (Cart cart : carts) {
//             new CartDAO().delete(cart.getId());
//         }

//         // Clean up orders after running the tests
//         List<Orders> orders = manageCart.getOrders();
//         for (Orders order : orders) {
//             new CartDAO().delete(order.getId());
//         }
//     }

//     @Test
//     public void testAddItemToCart() {
//         assertTrue(manageCart.addItemToCart(122, 1));
//     }

//     @Test
//     public void testRemoveItemFromCart() {
//         // Assuming there's an item in the cart, replace with actual item ID
//         assertTrue(manageCart.removeItemFromCart(122/* Replace with actual item ID */));
//     }

//     @Test
//     public void testUpdateItemQuantity() {
//         // Assuming there's an item in the cart, replace with actual item ID and quantity
//         assertTrue(manageCart.updateItemQuantity(122,5/* Replace with actual item ID */ /* Replace with actual quantity */));
//     }

//     @Test
//     public void testGetCartDetails() {
//         List<Cart> carts = manageCart.getCartDetails();
//         assertNotNull(carts);
//     }

//     @Test
//     public void testClearCart() {
//         assertTrue(manageCart.clearCart());
//     }

//     @Test
//     public void testPlaceOrder() {
//         // Assuming there are items in the cart, replace with actual customer name
//         assertTrue(manageCart.placeOrder("Customer1"/* Replace with actual customer name */));
//     }

//     @Test
//     public void testGetOrders() {
//         List<Orders> orders = manageCart.getOrders();
//         assertNotNull(orders);
//     }

//     @Test
//     public void testGetOrderDetails() {
//         // Assuming there's an order, replace with actual order ID
//         Orders order = manageCart.getOrderDetails(122/* Replace with actual order ID */);
//         assertNotNull(order);
//     }

//     @Test
//     public void testCancelOrder() {
//         // Assuming there's an order, replace with actual order ID
//         assertTrue(manageCart.cancelOrder(122/* Replace with actual order ID */));
//     }

//     @Test
//     public void testUpdateOrderStatus() {
//         // Assuming there's an order, replace with actual order ID and status
//         assertTrue(manageCart.updateOrderStatus(122,"st1"/* Replace with actual order ID */ /* Replace with actual status */));
//     }
// }

