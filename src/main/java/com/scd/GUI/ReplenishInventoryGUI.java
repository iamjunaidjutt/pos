package com.scd.GUI;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

import com.scd.Business.InventoryManagement;
import com.scd.Business.ManageCatalog;
import com.scd.Models.Category;
import com.scd.Models.Product;
import com.scd.Models.User;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReplenishInventoryGUI extends JFrame implements ActionListener {
    private User user;
    private JPanel formPanel;

    private JTable table;

    private DefaultTableModel tableModel;

    private JScrollPane scrollPane;

    private List<Product> products;

    private ManageCatalog manageCatalog;
    private InventoryManagement inventoryManagement;

    private JLabel pNameLabel, pPriceLabel, pStockQuantityLabel, pExpirationDateLabel;
    private JTextField pNameField, pPriceField, pStockQuantityField;

    private JSpinner dateSpinner;

    private JButton updateButton;

    private LocalDateTime convertToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public ReplenishInventoryGUI(User user) {
        super("Replenish Inventory");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Initialize
        inventoryManagement = new InventoryManagement(10);
        manageCatalog = new ManageCatalog();
        this.user = user;

        // Menu Bar
        MenuBarGUI menuBarGUI = new MenuBarGUI(this, user);
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
                            Product product = manageCatalog.getProduct(productCode);
                            pNameField.setText(product.getName());
                            pPriceField.setText(String.valueOf(product.getPrice()));
                            pStockQuantityField.setText(String.valueOf(product.getStockQuantity()));
                            dateSpinner.setValue(Date.from(product.getExpirationDate().atZone(ZoneId.systemDefault())
                                    .toInstant()));

                        } catch (Exception exception) {
                            System.out.println("Error: " + exception.getMessage());
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Select a product to update");
                    }
                }
            }
        });

        setVisible(true);
    }

    public void displayForm() {
        formPanel = new JPanel();
        formPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        formPanel.setPreferredSize(new Dimension(1200, 50));

        pNameLabel = new JLabel("Product Name");
        pNameField = new JTextField(15);
        pNameField.setEditable(false);

        pPriceLabel = new JLabel("Product Price");
        pPriceField = new JTextField(15);

        pStockQuantityLabel = new JLabel("Stock Quantity");
        pStockQuantityField = new JTextField(15);

        pExpirationDateLabel = new JLabel("Expiration Date");
        dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd.MM.yyyy");
        dateSpinner.setEditor(dateEditor);

        updateButton = new JButton("Update");
        updateButton.setFocusable(false);
        updateButton.addActionListener(this);

        formPanel.add(pNameLabel);
        formPanel.add(pNameField);
        formPanel.add(pPriceLabel);
        formPanel.add(pPriceField);
        formPanel.add(pStockQuantityLabel);
        formPanel.add(pStockQuantityField);
        formPanel.add(pExpirationDateLabel);
        formPanel.add(dateSpinner);
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
                            "[Edit]" });
        }

        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(1200, 750));
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
        table.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);

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
                            Product product = manageCatalog.getProduct(productCode);
                            pNameField.setText(product.getName());
                            pPriceField.setText(String.valueOf(product.getPrice()));
                            pStockQuantityField.setText(String.valueOf(product.getStockQuantity()));
                            dateSpinner.setValue(Date.from(product.getExpirationDate().atZone(ZoneId.systemDefault())
                                    .toInstant()));
                        } catch (Exception exception) {
                            System.out.println("Error: " + exception.getMessage());
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Select a product to update");
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
                            "[Edit]" });
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
        table.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        table.setModel(tableModel);
        scrollPane.setViewportView(table);
    }

    private void clearFields() {
        pNameField.setText("");
        pPriceField.setText("");
        pStockQuantityField.setText("");
        dateSpinner.setValue(new Date());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            try {
                int selectedRow = table.getSelectedRow();
                int productCode = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
                Product product = manageCatalog.getProduct(productCode);
                double price = Double.parseDouble(pPriceField.getText());
                int stockQuantity = Integer.parseInt(pStockQuantityField.getText());
                LocalDateTime expirationDate = convertToLocalDateTime((Date) dateSpinner.getValue());
                inventoryManagement.replenishInventory(product, stockQuantity, expirationDate, price);
                updateProducts();
                JOptionPane.showMessageDialog(null, "Product updated successfully");
                // Track expiration dates
                inventoryManagement.trackExpirationDates();
            } catch (Exception exception) {
                System.out.println("Error: " + exception.getMessage());
                JOptionPane.showMessageDialog(null, "Error updating product");
            }
            clearFields();
        }
    }

}
