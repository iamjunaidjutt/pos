package com.scd.GUI;

import javax.swing.*;

import com.scd.Models.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MenuBarGUI extends JMenuBar implements ActionListener {
    private User user;
    private JFrame parentFrame;

    public MenuBarGUI(JFrame parentFrame, User user) {
        this.user = user;
        this.parentFrame = parentFrame;

        // Add a menu as File
        JMenu inventoryMenu = new JMenu("Inventory");
        // fileMenu.setMnemonic('F');
        inventoryMenu.setMnemonic(KeyEvent.VK_I);

        // Create menu items for File menu
        JMenuItem replenishItem = new JMenuItem("Replenish Inventory");
        JMenuItem exitItem = new JMenuItem("Exit");

        // Add menu items in File menu
        inventoryMenu.add(replenishItem);
        inventoryMenu.addSeparator();
        inventoryMenu.add(exitItem);

        // Add action listeners to menu items in File menu
        replenishItem.addActionListener(this);
        exitItem.addActionListener(this);

        // Add a second menu as Item
        JMenu catalogMenu = new JMenu("Catalog");
        catalogMenu.setMnemonic('C');

        // Create menu Products for Item menu
        JMenuItem categories = new JMenuItem("Manage Categories");
        JMenuItem products = new JMenuItem("Manage Products");

        // Add menu Products in Item menu
        catalogMenu.add(categories);
        catalogMenu.add(products);

        // Add action listeners to menu Products in Item menu
        categories.addActionListener(this);
        products.addActionListener(this);

        // Add third menu for Sales
        JMenu salesMenu = new JMenu("Sales");
        salesMenu.setMnemonic('S');

        // Create menu items for Sales menu
        JMenuItem newSale = new JMenuItem("New Sale");
        JMenuItem viewCart = new JMenuItem("View Cart");
        JMenuItem viewOrders = new JMenuItem("View Orders");

        // Add menu items in Sales menu
        salesMenu.add(newSale);
        salesMenu.add(viewCart);
        salesMenu.add(viewOrders);

        // Add action listeners to menu items in Sales menu
        newSale.addActionListener(this);
        viewCart.addActionListener(this);
        viewOrders.addActionListener(this);

        // Add fourth menu for Reports
        JMenu reportsMenu = new JMenu("Reports");
        reportsMenu.setMnemonic('R');

        // Create menu items for Reports menu
        JMenuItem dailySalesReport = new JMenuItem("Daily Sales Report");
        JMenuItem weeklySalesReport = new JMenuItem("Weekly Sales Report");
        JMenuItem monthlySalesReport = new JMenuItem("Monthly Sales Report");
        JMenuItem inventoryReport = new JMenuItem("Inventory Report");

        // Add menu items in Reports menu
        reportsMenu.add(dailySalesReport);
        reportsMenu.add(weeklySalesReport);
        reportsMenu.add(monthlySalesReport);
        reportsMenu.add(inventoryReport);

        // Add action listeners to menu items in Reports menu
        dailySalesReport.addActionListener(this);
        weeklySalesReport.addActionListener(this);
        monthlySalesReport.addActionListener(this);
        inventoryReport.addActionListener(this);

        // Add menus in menu bar
        add(inventoryMenu);
        add(catalogMenu);
        add(salesMenu);
        add(reportsMenu);
    }

    /**
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Replenish Inventory":
                System.out.println("Replenish Inventory");
                System.out.println("User role: " + user.getRole());
                if (user.getRole().equals("MANAGER")) {
                    new ReplenishInventoryGUI(user);
                    parentFrame.setVisible(false);
                } else
                    JOptionPane.showMessageDialog(null, user.getRole() + " cannot replenish inventory");
                break;
            case "Exit":
                System.out.println("Exit");
                System.out.println("User role: " + user.getRole());
                System.exit(0);
                break;
            case "Manage Categories":
                System.out.println("Manage Categories");
                System.out.println("User role: " + user.getRole());
                if (user.getRole().equals("MANAGER")) {
                    new ManageCategoriesGUI(user);
                    parentFrame.setVisible(false);
                } else
                    JOptionPane.showMessageDialog(null, user.getRole() + " cannot manage categories");
                break;
            case "Manage Products":
                System.out.println("Manage Products");
                System.out.println("User role: " + user.getRole());
                if (user.getRole().equals("MANAGER")) {
                    new ManageProductsGUI(user);
                    parentFrame.setVisible(false);
                } else
                    JOptionPane.showMessageDialog(null, user.getRole() + " cannot manage products");
                break;
            case "New Sale":
                System.out.println("New Sale");
                System.out.println("User role: " + user.getRole());
                new ListAllProductsGUI(user);
                parentFrame.setVisible(false);
                break;
            case "View Cart":
                System.out.println("View Cart");
                System.out.println("User role: " + user.getRole());
                new ViewCartGUI(user);
                parentFrame.setVisible(false);
                break;
            case "View Orders":
                System.out.println("View Orders");
                System.out.println("User role: " + user.getRole());
                parentFrame.setVisible(false);
                new ViewOrdersGUI(user);
                break;
            case "Daily Sales Report":
                if (user.getRole().equals("MANAGER")) {
                    System.out.println("Daily Sales Report");
                    System.out.println("User role: " + user.getRole());
                    new ListAllProductsGUI(user);
                    parentFrame.setVisible(false);
                    // new SalesReportGUI();
                } else
                    JOptionPane.showMessageDialog(null, user.getRole() + " cannot view daily sales report");
                break;
            case "Weekly Sales Report":
                if (user.getRole().equals("MANAGER")) {
                    System.out.println("Weekly Sales Report");
                    System.out.println("User role: " + user.getRole());
                    new ListAllProductsGUI(user);
                    parentFrame.setVisible(false);
                    // new SalesReportGUI();
                } else
                    JOptionPane.showMessageDialog(null, user.getRole() + " cannot view weekly sales report");
                break;
            case "Monthly Sales Report":
                if (user.getRole().equals("MANAGER")) {
                    System.out.println("Monthly Sales Report");
                    System.out.println("User role: " + user.getRole());
                    new ListAllProductsGUI(user);
                    parentFrame.setVisible(false);
                    // new SalesReportGUI();
                } else
                    JOptionPane.showMessageDialog(null, user.getRole() + " cannot view monthly sales report");
                break;
            case "Inventory Report":
                if (user.getRole().equals("MANAGER")) {

                    System.out.println("Inventory Report");
                    System.out.println("User role: " + user.getRole());
                    // new InventoryReportGUI();
                    new ListAllProductsGUI(user);
                    parentFrame.setVisible(false);
                } else
                    JOptionPane.showMessageDialog(null, user.getRole() + " cannot view inventory report");
                break;
            default:
                System.out.println("Unknown command");
                break;
        }
    }
}