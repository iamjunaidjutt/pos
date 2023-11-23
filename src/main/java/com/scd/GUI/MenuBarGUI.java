package com.scd.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MenuBarGUI extends JMenuBar implements ActionListener {

    public MenuBarGUI(JFrame parentFrame) {
        // Add a menu as File
        JMenu fileMenu = new JMenu("File");
        // fileMenu.setMnemonic('F');
        fileMenu.setMnemonic(KeyEvent.VK_F);

        // Create menu items for File menu
        JMenuItem newSaleItem = new JMenuItem("New Sale");
        JMenuItem openSaleItem = new JMenuItem("Open Sale");
        JMenuItem saveSaleItem = new JMenuItem("Save Sale");
        JMenuItem exitItem = new JMenuItem("Exit");

        // Add menu items in File menu
        fileMenu.add(newSaleItem);
        fileMenu.add(openSaleItem);
        fileMenu.add(saveSaleItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Add action listeners to menu items in File menu
        newSaleItem.addActionListener(this);
        openSaleItem.addActionListener(this);
        saveSaleItem.addActionListener(this);
        exitItem.addActionListener(this);

        // Add a second menu as Item
        JMenu itemMenu = new JMenu("Item");
        itemMenu.setMnemonic('I');

        // Create menu items for Item menu
        JMenuItem newItem = new JMenuItem("New Item");
        JMenuItem deleteItem = new JMenuItem("Delete Item");
        JMenuItem updateItem = new JMenuItem("Update Item");
        JMenuItem listItems = new JMenuItem("List Items");

        // Add menu items in Item menu
        itemMenu.add(newItem);
        itemMenu.add(deleteItem);
        itemMenu.add(updateItem);
        itemMenu.add(listItems);

        // Add action listeners to menu items in Item menu
        newItem.addActionListener(this);
        deleteItem.addActionListener(this);
        updateItem.addActionListener(this);
        listItems.addActionListener(this);

        // Add menus in menu bar
        add(fileMenu);
        add(itemMenu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "New Sale":
                System.out.println("New Sale");
                break;
            case "Open Sale":
                System.out.println("Open Sale");
                break;
            case "Save Sale":
                System.out.println("Save Sale");
                break;
            case "Exit":
                System.out.println("Exit");
                System.exit(0);
                break;
            case "New Item":
                System.out.println("New Item");
                break;
            case "Delete Item":
                System.out.println("Delete Item");
                break;
            case "Update Item":
                System.out.println("Update Item");
                break;
            case "List Items":
                System.out.println("List Items");
                break;
            default:
                System.out.println("Unknown command");
                break;
        }
    }
}