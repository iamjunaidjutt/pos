package com.scd.GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListAllProducts extends JFrame implements ActionListener {
    JLabel titleLabel;

    JTextField searchField;

    JPanel panel1;
    JPanel panel2;

    public ListAllProducts() {
        super("List All Products");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Menu Bar
        MenuBarGUI menuBarGUI = new MenuBarGUI(this);
        setJMenuBar(menuBarGUI);

        // Title
        titleLabel = new JLabel("Search for a product");

        // Search Field
        searchField = new JTextField(20);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

}
