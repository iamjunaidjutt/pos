package com.scd.Business;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.scd.Data.CategoryDAO;
import com.scd.Data.ItemDAO;
import com.scd.Data.OrdersDAO;
import com.scd.Models.Category;
import com.scd.Models.CategorySales;
import com.scd.Models.Item;
import com.scd.Models.Orders;

public class DailySalesReport {

    public List<CategorySales> getCategoriesSales(LocalDateTime to, LocalDateTime from) {
        List<CategorySales> categoriesSales = new ArrayList<>();
        List<Object> categoriesObjects = new CategoryDAO().getAll();
        List<Category> categories = new ArrayList<>();
        for (Object object : categoriesObjects) {
            categories.add((Category) object);
        }
        System.out.println("Categories: " + categories);
        List<Orders> orders = new OrdersDAO().getOrdersByDate(to, from);
        List<Object> itemsObjects = new ItemDAO().getAll();
        List<Item> items = new ArrayList<>();
        for (Object object : itemsObjects) {
            items.add((Item) object);
        }
        System.out.println("Items: " + items);
        for (Category category : categories) {
            CategorySales categorySales = new CategorySales();
            categorySales.setName(category.getName());
            for (Orders order : orders) {
                for (Item item : items) {
                    if (item.getOrder().getId() == order.getId()
                            && item.getProduct().getCategories().contains(category)) {
                        categorySales.setQuantity(item.getQuantityOrdered());
                        categorySales.setTotal(item.getPrice());
                    }
                }
            }
            categoriesSales.add(categorySales);
        }
        System.out.println(categoriesSales);
        return categoriesSales;
    }
}
