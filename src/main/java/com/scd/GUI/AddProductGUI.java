package com.scd.GUI;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.scd.Business.ManageCatalog;
import com.scd.Models.Category;
import com.scd.Models.Product;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddProductGUI extends JFrame implements ActionListener {
    private JPanel formPanel;

    private JTable table;

    private DefaultTableModel tableModel;

    private JScrollPane scrollPane;

    private List<Product> products;

    private ManageCatalog manageCatalog;

    private JLabel pNameLabel, pDescriptionLabel, pPriceLabel, pStockQuantityLabel, pExpirationDateLabel,
            pCategoriesLabel;
    private JTextField pNameField, pDescriptionField, pPriceField, pStockQuantityField;

    private JSpinner dateSpinner;

    private JComboBox<String> categoriesComboBox;
    private List<String> selectedCategories;

    private JList<String> categoriesList;
    private DefaultListModel<String> categoriesListModel;
    private JScrollPane categoriesScrollPane;

    private JButton removeButton;
    private JButton addButton;
    private JButton createButton;
    private JButton updateButton;

    private LocalDateTime convertToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public AddProductGUI() {
        super("List All Products");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Initialize ManageCatalog
        manageCatalog = new ManageCatalog();
        selectedCategories = new ArrayList<>();

        // Menu Bar
        MenuBarGUI menuBarGUI = new MenuBarGUI(this);
        setJMenuBar(menuBarGUI);

        // Panels

        displayForm();
        displayProducts();

        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = table.getSelectedRow();
                int selectedColumn = table.getSelectedColumn();

                if (selectedRow != -1) {
                    if (selectedColumn == 7) {
                        try {
                            int productCode = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
                            if (manageCatalog.deleteProduct(productCode)) {
                                JOptionPane.showMessageDialog(null, "Product deleted successfully");
                                updateProducts();
                            } else {
                                JOptionPane.showMessageDialog(null, "Error deleting product");
                            }
                        } catch (Exception exception) {
                            System.out.println("Error: " + exception.getMessage());
                        }
                    } else {
                        pNameField.setText(table.getValueAt(selectedRow, 1).toString());
                        pDescriptionField.setText(table.getValueAt(selectedRow, 2).toString());
                        pPriceField.setText(table.getValueAt(selectedRow, 4).toString());
                        pStockQuantityField.setText(table.getValueAt(selectedRow, 5).toString());
                        dateSpinner.setValue(table.getValueAt(selectedRow, 6));
                        List<Category> categories = manageCatalog.getCategoriesByProduct(
                                Integer.parseInt(table.getValueAt(selectedRow, 0).toString()));
                        selectedCategories = new ArrayList<>();
                        for (Category category : categories) {
                            selectedCategories.add(category.getName());
                        }
                        updateListModel();
                    }
                }
            }
        });

        setVisible(true);
    }

    private void updateListModel() {
        categoriesListModel = new DefaultListModel<>();
        for (String selectedCategory : selectedCategories) {
            categoriesListModel.addElement(selectedCategory);
        }

        if (categoriesList != null) {
            categoriesList.setModel(categoriesListModel);
            categoriesList.repaint();
        } else {
            categoriesList = new JList<>(categoriesListModel);
        }
    }

    public void displayForm() {
        formPanel = new JPanel();
        formPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        formPanel.setPreferredSize(new Dimension(1200, 150));

        pNameLabel = new JLabel("Product Name");
        pNameField = new JTextField(15);

        pDescriptionLabel = new JLabel("Product Description");
        pDescriptionField = new JTextField(15);

        pPriceLabel = new JLabel("Product Price");
        pPriceField = new JTextField(15);

        pStockQuantityLabel = new JLabel("Stock Quantity");
        pStockQuantityField = new JTextField(15);

        pExpirationDateLabel = new JLabel("Expiration Date");
        dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd.MM.yyyy");
        dateSpinner.setEditor(dateEditor);

        pCategoriesLabel = new JLabel("Categories");

        List<Category> categories = manageCatalog.getAllCategories();

        updateListModel();

        String[] categoriesArray = new String[categories.size()];
        for (int i = 0; i < categories.size(); i++) {
            categoriesArray[i] = categories.get(i).getName();
        }
        categoriesComboBox = new JComboBox<>(categoriesArray);

        categoriesScrollPane = new JScrollPane(categoriesList);
        categoriesScrollPane.setPreferredSize(new Dimension(200, 100));

        addButton = new JButton("+");
        addButton.setFocusable(false);
        addButton.addActionListener(this);

        removeButton = new JButton("-");
        removeButton.setFocusable(false);
        removeButton.addActionListener(this);

        createButton = new JButton("Create");
        createButton.setFocusable(false);
        createButton.addActionListener(this);

        updateButton = new JButton("Update");
        updateButton.setFocusable(false);
        updateButton.addActionListener(this);

        formPanel.add(pNameLabel);
        formPanel.add(pNameField);
        formPanel.add(pDescriptionLabel);
        formPanel.add(pDescriptionField);
        formPanel.add(pPriceLabel);
        formPanel.add(pPriceField);
        formPanel.add(pStockQuantityLabel);
        formPanel.add(pStockQuantityField);
        formPanel.add(pExpirationDateLabel);
        formPanel.add(dateSpinner);
        formPanel.add(pCategoriesLabel);
        formPanel.add(categoriesScrollPane);
        formPanel.add(removeButton);
        formPanel.add(categoriesComboBox);
        formPanel.add(addButton);
        formPanel.add(createButton);
        formPanel.add(updateButton);

        add(formPanel);
    }

    public void displayProducts() {
        products = manageCatalog.getAllProducts();

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(
                new String[] { "Product Code", "Product Name", "Product Description", "Category", "Product Price",
                        "Stock Available",
                        "Expiration Date", "Actions" });
        for (Product product : products) {
            List<String> categories = new ArrayList<>();
            for (Category category : product.getCategories()) {
                categories.add(category.getName());
            }
            tableModel.addRow(
                    new Object[] { product.getCode(), product.getName(), product.getDescription(), categories,
                            String.valueOf(product.getPrice()), String.valueOf(product.getStockQuantity()),
                            product.getExpirationDate(),
                            "X" });
        }

        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(1200, 700));
        table.setFillsViewportHeight(true);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);

        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = table.getSelectedRow();
                int selectedColumn = table.getSelectedColumn();

                if (selectedRow != -1) {
                    if (selectedColumn == 7) {
                        try {
                            int productCode = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
                            if (manageCatalog.deleteProduct(productCode)) {
                                JOptionPane.showMessageDialog(null, "Product deleted successfully");
                                updateProducts();
                            } else {
                                JOptionPane.showMessageDialog(null, "Error deleting product");
                            }
                            table.revalidate();
                            table.repaint();
                        } catch (Exception exception) {
                            System.out.println("Error: " + exception.getMessage());
                        }
                    } else {
                        pNameField.setText(table.getValueAt(selectedRow, 1).toString());
                        pDescriptionField.setText(table.getValueAt(selectedRow, 2).toString());
                        pPriceField.setText(table.getValueAt(selectedRow, 4).toString());
                        pStockQuantityField.setText(table.getValueAt(selectedRow, 5).toString());
                        dateSpinner.setValue(table.getValueAt(selectedRow, 6));
                        List<Category> categories = manageCatalog.getCategoriesByProduct(
                                Integer.parseInt(table.getValueAt(selectedRow, 0).toString()));
                        selectedCategories = new ArrayList<>();
                        for (Category category : categories) {
                            selectedCategories.add(category.getName());
                        }
                        updateListModel();
                    }
                }
            }

        });

        scrollPane = new JScrollPane(table);

        add(scrollPane);
    }

    public void updateProducts() {
        products = manageCatalog.getAllProducts();

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(
                new String[] { "Product Code", "Product Name", "Product Description", "Category", "Product Price",
                        "Stock Available",
                        "Expiration Date", "Actions" });
        for (Product product : products) {
            List<String> categories = new ArrayList<>();
            for (Category category : product.getCategories()) {
                categories.add(category.getName());
            }
            tableModel.addRow(
                    new Object[] { product.getCode(), product.getName(), product.getDescription(), categories,
                            String.valueOf(product.getPrice()), String.valueOf(product.getStockQuantity()),
                            product.getExpirationDate(),
                            "X" });
        }

        table.setPreferredScrollableViewportSize(new Dimension(1200, 700));
        table.setFillsViewportHeight(true);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        table.setModel(tableModel);
        scrollPane.setViewportView(table);
    }

    private void clearFields() {
        pNameField.setText("");
        pDescriptionField.setText("");
        pPriceField.setText("");
        pStockQuantityField.setText("");
        dateSpinner.setValue(new Date());
        selectedCategories = new ArrayList<>();
        updateListModel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            System.out.println("Select category button clicked");
            String selectedCategory = categoriesComboBox.getSelectedItem().toString();
            selectedCategories.add(selectedCategory);
            updateListModel();
            System.out.println(selectedCategories);
        } else if (e.getSource() == removeButton) {
            System.out.println("Remove category button clicked");
            String selectedCategory = categoriesComboBox.getSelectedItem().toString();
            selectedCategories.remove(selectedCategory);
            updateListModel();
            System.out.println(selectedCategories);
        } else if (e.getSource() == createButton) {
            System.out.println("Create button clicked");
            String productName = pNameField.getText();
            String productDescription = pDescriptionField.getText();
            double productPrice = Double.parseDouble(pPriceField.getText());
            int productStockQuantity = Integer.parseInt(pStockQuantityField.getText());
            List<Category> productCategories = new ArrayList<>();
            for (String selectedCategory : selectedCategories) {
                Category category = manageCatalog.getCategoryByName(selectedCategory);
                productCategories.add(category);
            }
            if (manageCatalog.addProduct(productName, productDescription, productPrice, productStockQuantity,
                    convertToLocalDateTime((Date) dateSpinner.getValue()),
                    productCategories)) {
                JOptionPane.showMessageDialog(null, "Product added successfully");
                updateProducts();
            } else {
                JOptionPane.showMessageDialog(null, "Error adding product");
            }
            clearFields();
        } else if (e.getSource() == updateButton) {
            System.out.println("Update button clicked");
            int productCode = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
            String productName = pNameField.getText();
            String productDescription = pDescriptionField.getText();
            double productPrice = Double.parseDouble(pPriceField.getText());
            int productStockQuantity = Integer.parseInt(pStockQuantityField.getText());
            List<Category> productCategories = new ArrayList<>();
            for (String selectedCategory : selectedCategories) {
                Category category = manageCatalog.getCategoryByName(selectedCategory);
                productCategories.add(category);
            }
            if (manageCatalog.updateProduct(productCode, productName, productDescription, productPrice,
                    productStockQuantity,
                    convertToLocalDateTime((Date) dateSpinner.getValue()),
                    productCategories)) {
                JOptionPane.showMessageDialog(null, "Product updated successfully");
                updateProducts();
            } else {
                JOptionPane.showMessageDialog(null, "Error updating product");
            }
            clearFields();
        }
    }

}

