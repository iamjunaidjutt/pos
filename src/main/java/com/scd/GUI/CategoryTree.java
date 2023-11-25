package com.scd.GUI;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class CategoryTree extends JFrame {

    public CategoryTree() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Category Tree Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create sample data
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Categories");

        DefaultMutableTreeNode electronics = new DefaultMutableTreeNode("Electronics");
        electronics.add(new DefaultMutableTreeNode("Laptops"));
        electronics.add(new DefaultMutableTreeNode("Smartphones"));

        DefaultMutableTreeNode clothing = new DefaultMutableTreeNode("Clothing");
        clothing.add(new DefaultMutableTreeNode("Men's Clothing"));
        clothing.add(new DefaultMutableTreeNode("Women's Clothing"));

        root.add(electronics);
        root.add(clothing);

        // Create JTree with the sample data
        JTree categoryTree = new JTree(root);

        // Customize the appearance
        categoryTree.setRootVisible(false);
        categoryTree.setShowsRootHandles(true);

        // Add the tree to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(categoryTree);

        // Add the JScrollPane to the JFrame
        add(scrollPane, BorderLayout.CENTER);

        // Set frame properties
        setSize(300, 400);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CategoryTree().setVisible(true);
            }
        });
    }

}
