package com.scd.Business;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.scd.Data.CartDAO;
import com.scd.Data.ItemDAO;
import com.scd.Data.OrdersDAO;
import com.scd.Models.Cart;
import com.scd.Models.Customer;
import com.scd.Models.Item;
import com.scd.Models.Orders;
import com.scd.Models.Product;

public class ManageCart {
    CartDAO cartDAO;

    public ManageCart() {
        cartDAO = new CartDAO();
    }

    public boolean addItemToCart(int productId, int quantityOrdered) {
        ManageCatalog manageCatalog = new ManageCatalog();
        try {
            Product product = manageCatalog.getProduct(productId);
            if (product.getStockQuantity() < quantityOrdered) {
                return false;
            }
            Item item = new Item();
            item.setProduct(product);
            item.setPrice(product.getPrice() * quantityOrdered);
            item.setQuantityOrdered(quantityOrdered);
            Cart cart = new Cart();
            cart.addItem(item);
            cartDAO.save(cart);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeItemFromCart(int id) {
        ItemDAO itemDAO = new ItemDAO();
        return itemDAO.delete(id);
    }

    public boolean updateItemQuantity(int id, int quantityOrdered) {
        ItemDAO itemDAO = new ItemDAO();
        Item item = itemDAO.getById(id);
        item.setQuantityOrdered(quantityOrdered);
        item.setPrice(item.getProduct().getPrice() * quantityOrdered);
        return itemDAO.update(item);
    }

    public List<Cart> getCartDetails() {
        List<Object> objects = cartDAO.getAll();
        List<Cart> carts = new ArrayList<>();
        for (Object object : objects) {
            carts.add((Cart) object);
        }
        return carts;
    }

    public boolean clearCart() {
        try {
            List<Cart> carts = getCartDetails();
            for (Cart cart : carts) {
                cartDAO.delete(cart.getId());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean placeOrder(String customerName) {
        try {
            ItemDAO itemDAO = new ItemDAO();
            List<Object> itemsObjects = itemDAO.getAll();
            List<Item> items = new ArrayList<>();
            for (Object object : itemsObjects) {
                items.add((Item) object);
            }
            clearCart();
            Orders order = new Orders();
            for (Item item : items) {
                order.addItem(item);
            }
            order.setDate(LocalDateTime.now());
            order.setStatus("Pending");
            int orderTotal = 0;
            for (Item item : items) {
                orderTotal += item.getPrice();
            }
            order.setTotal(orderTotal);
            Customer customer = new Customer();
            customer.setName(customerName);
            order.setCustomer(customer);
            OrdersDAO ordersDAO = new OrdersDAO();
            ordersDAO.update(order);
            InventoryManagement inventoryManagement = new InventoryManagement(10);
            inventoryManagement.updateInventory(items);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Orders> getOrders() {
        OrdersDAO ordersDAO = new OrdersDAO();
        List<Object> objects = ordersDAO.getAll();
        List<Orders> orders = new ArrayList<>();
        for (Object object : objects) {
            orders.add((Orders) object);
        }
        return orders;
    }

    public Orders getOrderDetails(int orderId) {
        OrdersDAO ordersDAO = new OrdersDAO();
        return ordersDAO.getById(orderId);
    }

    public boolean cancelOrder(int orderId) {
        OrdersDAO ordersDAO = new OrdersDAO();
        return ordersDAO.delete(orderId);
    }

    public boolean updateOrderStatus(int orderId, String status) {
        OrdersDAO ordersDAO = new OrdersDAO();
        Orders order = ordersDAO.getById(orderId);
        order.setStatus(status);
        return ordersDAO.update(order);
    }

    // public static void main(String[] args) {
    // ManageCart manageCart = new ManageCart();

    // manageCart.addItemToCart(122, 3);
    // manageCart.addItemToCart(123, 3);

    // manageCart.removeItemFromCart(169);

    // manageCart.updateItemQuantity(173, 5);

    // manageCart.clearCart();

    // List<Cart> carts = manageCart.getCartDetails();
    // for (Cart cart : carts) {
    // System.out.println(cart);
    // }

    // manageCart.placeOrder("John Doe");

    // manageCart.cancelOrder(212);

    // List<Orders> orders = manageCart.getOrders();
    // for (Orders order : orders) {
    // System.out.println(order);
    // }

    // }
}
