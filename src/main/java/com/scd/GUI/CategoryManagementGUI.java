package com.scd.GUI;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.scd.Business.ManageCatalog;
import com.scd.Models.Category;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CategoryManagementGUI extends JFrame implements ActionListener {

    private JPanel panel1;
    private JPanel panel2;

    private JLabel nameLabel, descriptionLabel, parentLabel, msgLabel;

    private JTextField nameField, parentField;

    private JTextArea descriptionField;

    private JButton createButton, updateButton, deleteButton;

    private JTree categoryTree;

    private List<Category> categories;

    private ManageCatalog manageCatalog;

    private Category selectedCategory;

    private Category parentCategory;

    private DefaultMutableTreeNode addNodes(DefaultMutableTreeNode root, List<Category> categories) {
        for (Category category : categories) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(
                    category.getName() + " - " + category.getDescription());
            root.add(node);
            System.out.println("Category: " + category.getName());
            List<Category> subCategories = manageCatalog.getAllSubCategories(category);
            System.out.println("Subcategories: " + subCategories.size());
            if (subCategories.size() > 0) {
                addNodes(node, subCategories);
            }
        }

        return root;
    }

    public CategoryManagementGUI() {
        super("Category Management");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Initialize ManageCatalog
        manageCatalog = new ManageCatalog();

        // Menu Bar
        MenuBarGUI menuBarGUI = new MenuBarGUI(this);
        setJMenuBar(menuBarGUI);

        panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(1200, 800));
        panel2.setLayout(new GridLayout(1, 2));

        categoryTree = displayCategoryTree();

        displayCategoryForm();
        panel2.add(panel1);
        panel2.add(categoryTree);

        add(panel2);

        setVisible(true);
    }

    public void displayCategoryForm() {
        panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(600, 800));
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

        Font font = new Font("Arial", Font.BOLD, 15);

        nameLabel = new JLabel("Name");
        nameLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        nameLabel.setFont(font);
        nameField = new JTextField(30);
        nameField.setMaximumSize(new Dimension(300, 30));
        nameField.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        nameField.setFont(font);

        descriptionLabel = new JLabel("Description");
        descriptionLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        descriptionLabel.setFont(font);
        descriptionField = new JTextArea(5, 30);
        descriptionField.setMaximumSize(new Dimension(300, 30));
        descriptionField.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        descriptionField.setFont(font);

        msgLabel = new JLabel(
                "Select a CATEGORY for updating, deleting, or adding a subcategory.");
        msgLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        msgLabel.setFont(new Font(null, Font.ITALIC, 12));
        msgLabel.setForeground(java.awt.Color.RED);

        parentLabel = new JLabel("Parent Category");
        parentLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        parentLabel.setFont(font);
        parentField = new JTextField(30);
        parentField.setMaximumSize(new Dimension(300, 30));
        parentField.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        parentField.setFont(font);
        parentField.setEditable(false);

        createButton = new JButton("Create");
        createButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        createButton.setMaximumSize(new Dimension(300, 30));
        createButton.setFocusable(false);
        createButton.setFont(font);
        createButton.addActionListener(this);

        updateButton = new JButton("Update");
        updateButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        updateButton.setMaximumSize(new Dimension(300, 30));
        updateButton.setFocusable(false);
        updateButton.setFont(font);
        updateButton.setVisible(false);
        updateButton.addActionListener(this);

        deleteButton = new JButton("Delete");
        deleteButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        deleteButton.setMaximumSize(new Dimension(300, 30));
        deleteButton.setFocusable(false);
        deleteButton.setFont(font);
        deleteButton.setVisible(false);
        deleteButton.addActionListener(this);

        panel1.add(Box.createVerticalGlue());
        panel1.add(nameLabel);
        panel1.add(Box.createVerticalStrut(10));
        panel1.add(nameField);
        panel1.add(Box.createVerticalStrut(20));
        panel1.add(descriptionLabel);
        panel1.add(Box.createVerticalStrut(10));
        panel1.add(descriptionField);
        panel1.add(Box.createVerticalStrut(20));
        panel1.add(msgLabel);
        panel1.add(Box.createVerticalStrut(10));
        panel1.add(parentLabel);
        panel1.add(Box.createVerticalStrut(10));
        panel1.add(parentField);
        panel1.add(Box.createVerticalStrut(20));
        panel1.add(createButton);
        panel1.add(Box.createVerticalStrut(10));
        panel1.add(updateButton);
        panel1.add(Box.createVerticalStrut(10));
        panel1.add(deleteButton);
        panel1.add(Box.createVerticalGlue());

    }

    public JTree displayCategoryTree() {
        categories = manageCatalog.getAllCategories();

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Categories");
        root = addNodes(root, categories);

        JTree tree = new JTree(root);

        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                TreePath selectedPath = categoryTree.getSelectionPath(); // Corrected this line
                if (selectedPath != null) {
                    Object selectedNode = selectedPath.getLastPathComponent();
                    selectedNode = selectedNode.toString().split(" - ")[0];
                    System.out.println("Selected Node: " + selectedNode);
                    if (selectedNode.toString().equals("Categories")) {
                        System.out.println("No category selected");
                        nameField.setText("");
                        descriptionField.setText("");
                        parentField.setText("");
                        updateButton.setVisible(false);
                        deleteButton.setVisible(false);
                    } else {
                        updateButton.setVisible(true);
                        deleteButton.setVisible(true);
                        List<String> parentList = new ArrayList<>();
                        for (int i = 0; i < selectedPath.getPathCount(); i++) {
                            parentList.add(selectedPath.getPathComponent(i).toString().split(" [-] ")[0]);
                        }
                        selectedCategory = manageCatalog.getCategoryByName(parentList.get(parentList.size() - 1));
                        if (parentList.size() < 2) {
                            parentField.setText(""); // Corrected this line
                            parentCategory = null;
                        } else if (parentList.size() == 2) {
                            parentField.setText(parentList.get(parentList.size() - 1));
                            parentCategory = null;
                        } else if (parentList.size() > 2) {
                            parentField.setText(parentList.get(parentList.size() - 2));
                            parentCategory = manageCatalog.getCategoryByName(parentList.get(parentList.size() - 2));
                        }
                        System.out.println("Selected Category: " + selectedCategory.getName());
                        System.out.println(
                                "Parent Category: " + (parentCategory != null ? parentCategory.getName() : "N/A"));
                        nameField.setText(selectedCategory.getName());
                        descriptionField.setText(selectedCategory.getDescription());
                    }
                }
            }
        });

        return tree;
    }

    public static void main(String[] args) {
        CategoryManagementGUI categoryManagementGUI = new CategoryManagementGUI();
        categoryManagementGUI.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton) {
            if (nameField.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter a name for the category.");
            } else {
                try {
                    Category category = new Category();
                    category.setName(nameField.getText());
                    category.setDescription(descriptionField.getText());
                    if (parentField.getText().equals("")) {
                        manageCatalog.addCategory(category.getName(), category.getDescription());
                    } else {
                        manageCatalog.addSubCategory(manageCatalog.getCategoryByName(parentField.getText()).getCode(),
                                category);
                    }
                    categoryTree = displayCategoryTree();
                    panel2.remove(1);
                    panel2.add(categoryTree);
                    panel2.revalidate();
                    panel2.repaint();
                    JOptionPane.showMessageDialog(this, "Category created successfully.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error creating category.");
                }
            }
        } else if (e.getSource() == updateButton) {
            if (nameField.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter a name for the category.");
            } else {
                try {
                    selectedCategory.setName(nameField.getText());
                    selectedCategory.setDescription(descriptionField.getText());
                    if (manageCatalog.updateCategory(selectedCategory.getCode(), selectedCategory.getName(),
                            selectedCategory.getDescription())) {
                        categoryTree = displayCategoryTree();
                        panel2.remove(1);
                        panel2.add(categoryTree);
                        panel2.revalidate();
                        panel2.repaint();
                        JOptionPane.showMessageDialog(this, "Category updated successfully.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Error updating category.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error updating category.");
                }
            }
        } else if (e.getSource() == deleteButton) {
            if (nameField.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter a name for the category.");
            } else {
                try {
                    if (parentCategory == null) {
                        if (manageCatalog.deleteCategory(selectedCategory.getCode())) {
                            categoryTree = displayCategoryTree();
                            panel2.remove(1);
                            panel2.add(categoryTree);
                            panel2.revalidate();
                            panel2.repaint();
                            JOptionPane.showMessageDialog(this, "Category deleted successfully.");
                        } else {
                            JOptionPane.showMessageDialog(this, "Error deleting category.");
                        }
                    } else {
                        if (manageCatalog.deleteSubCategory(parentCategory, selectedCategory)) {
                            categoryTree = displayCategoryTree();
                            panel2.remove(1);
                            panel2.add(categoryTree);
                            panel2.revalidate();
                            panel2.repaint();
                            JOptionPane.showMessageDialog(this, "Category deleted successfully.");
                        } else {
                            JOptionPane.showMessageDialog(this, "Error deleting category.");

                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error deleting category.");
                }
            }
        }
    }

}
