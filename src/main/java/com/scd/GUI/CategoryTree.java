package com.scd.GUI;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import java.awt.*;

public class CategoryTree extends JFrame {
    JTree categoryTree;

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

        DefaultMutableTreeNode books = new DefaultMutableTreeNode("Books");

        DefaultMutableTreeNode fiction = new DefaultMutableTreeNode("Fiction");
        fiction.add(new DefaultMutableTreeNode("Thriller"));
        fiction.add(new DefaultMutableTreeNode("Mystery"));
        fiction.add(new DefaultMutableTreeNode("Romance"));

        DefaultMutableTreeNode nonfiction = new DefaultMutableTreeNode("Non-fiction");
        nonfiction.add(new DefaultMutableTreeNode("Biography"));
        nonfiction.add(new DefaultMutableTreeNode("History"));
        nonfiction.add(new DefaultMutableTreeNode("Science"));

        books.add(fiction);
        books.add(nonfiction);

        root.add(books);

        root.add(electronics);
        root.add(clothing);

        // Create JTree with the sample data
        categoryTree = new JTree(root);

        // Customize the appearance
        categoryTree.setRootVisible(false);
        categoryTree.setShowsRootHandles(true);

        // Add a selection listener to the tree
        categoryTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                TreePath selectedPath = categoryTree.getSelectionPath();
                if (selectedPath != null) {
                    Object selectedNode = selectedPath.getLastPathComponent();
                    System.out.println("Selected Node: " + selectedNode);
                }
            }
        });

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
