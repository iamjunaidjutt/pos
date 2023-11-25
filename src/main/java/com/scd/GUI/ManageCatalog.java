package com.scd.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;

import com.scd.Data.CategoryDAO;
import com.scd.Data.ItemDAO;
import com.scd.Data.ProductDAO;
import com.scd.Models.Category;
import com.scd.Models.Item;
import com.scd.Models.Product;

public class ManageCatalog extends JFrame implements ActionListener {

    public ManageCatalog() {
        super("Manage Catalog");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Menu Bar
        MenuBarGUI menuBarGUI = new MenuBarGUI(this);
        setJMenuBar(menuBarGUI);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        Collection<Object> itemsObjects = new ArrayList<>();
        ItemDAO itemDAO = new ItemDAO();
        itemsObjects = itemDAO.getAll();

        for (Object itemObject : itemsObjects) {
            items.add((Item) itemObject);
        }

        for (Item item : items) {
            System.out.println(item);
        }

        itemDAO.delete(items.get(0).getCode());
    }

}
