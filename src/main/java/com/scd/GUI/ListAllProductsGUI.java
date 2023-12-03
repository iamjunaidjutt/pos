package com.scd.GUI;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.scd.Business.ManageCart;
import com.scd.Business.ManageCatalog;
import com.scd.Models.Category;
import com.scd.Models.Product;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

public class ListAllProductsGUI extends JFrame {
    private JLabel titleLabel;

    private JTextField searchField;

    private JPanel panel1;
    private JPanel panel2;

    private JTable table;

    private DefaultTableModel tableModel;

    private JTree categoryTree;

    private JScrollPane scrollPane;

    private List<Category> categories;

    private List<Product> products;

    private ManageCatalog manageCatalog;

    private DefaultMutableTreeNode addNodes(DefaultMutableTreeNode root, List<Category> categories) {
        for (Category category : categories) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(category.getName());
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

    public ListAllProductsGUI() {
        super("List All Products");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Initialize ManageCatalog
        manageCatalog = new ManageCatalog();

        // Menu Bar
        MenuBarGUI menuBarGUI = new MenuBarGUI(this);
        setJMenuBar(menuBarGUI);

        // Title
        titleLabel = new JLabel("Search for a product");
        // titleLabel.setBounds(20, 20, 200, 30);

        // Search Field
        searchField = new JTextField(20);
        searchField.setPreferredSize(new Dimension(200, 30));
        // searchField.setBounds(20, 50, 200, 30);

        // Panels
        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.setPreferredSize(new Dimension(1200, 50));

        panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(1200, 750));
        panel2.setLayout(new GridLayout(1, 2));

        panel1.add(titleLabel);
        panel1.add(searchField);

        categoryTree = displayCategoryTree();

        categoryTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                TreePath selectedPath = categoryTree.getSelectionPath();
                if (selectedPath != null) {
                    Object selectedNode = selectedPath.getLastPathComponent();
                    System.out.println("Selected Node: " + selectedNode);
                    if (selectedNode.toString().equals("Categories")) {
                        System.out.println("No category selected");
                    } else {
                        Category category = manageCatalog.getCategoryByName(selectedNode.toString());
                        System.out.println("Category: " + category.getName());
                        updateProducts(category);
                    }
                }
            }
        });

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                displayProducts();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                displayProducts();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Plain text components do not fire these events
            }
        });

        panel2.add(categoryTree);
        panel2.add(displayProducts(null));

        add(panel1);
        add(panel2);

        setVisible(true);
    }

    public JTree displayCategoryTree() {
        categories = manageCatalog.getAllCategories();

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Categories");
        root = addNodes(root, categories);

        JTree tree = new JTree(root);

        return tree;
    }

    public JScrollPane displayProducts(Category category) {
        if (category == null) {
            products = manageCatalog.getAllProducts();
        } else {
            products = category.getProducts();
        }
        System.out.println("Products: " + products.size());

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(
                new String[] { "Product Name", "Product Description", "Product Price", "Stock Available", "Actions" });
        for (Product product : products) {
            tableModel.addRow(
                    new Object[] { product.getName(), product.getDescription(),
                            String.valueOf(product.getPrice()), String.valueOf(product.getStockQuantity()),
                            "+" });
        }

        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(600, 750));
        table.setFillsViewportHeight(true);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        scrollPane = new JScrollPane(table);

        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = table.getSelectedRow();
                int selectedColumn = table.getSelectedColumn();
                if (selectedRow != -1 && selectedColumn == 4) {
                    try {
                        String productName = table.getValueAt(selectedRow, 0).toString();
                        Product product = manageCatalog.getProductByName(productName);
                        System.out.println("Product: " + product.getName());
                        String quantity = JOptionPane.showInputDialog("Enter quantity");
                        int quantityInt = Integer.parseInt(quantity);
                        if (quantityInt > product.getStockQuantity()) {
                            JOptionPane.showMessageDialog(null, "Not enough stock available");
                        } else {
                            try {
                                ManageCart manageCart = new ManageCart();
                                manageCart.addItemToCart(product.getCode(), quantityInt);
                                JOptionPane.showMessageDialog(null, "Product added to cart");
                            } catch (Exception exception) {
                                System.out.println("Error: " + exception.getMessage());
                                JOptionPane.showMessageDialog(null, "Error: " + exception.getMessage());
                            }
                        }
                    } catch (Exception exception) {
                        System.out.println("Error: " + exception.getMessage());
                    }
                } else {
                    System.out.println("No product selected");
                }
            }
        });

        return scrollPane;
    }

    public void updateProducts(Category category) {
        products = category.getProducts();
        System.out.println("Products: " + products.size());

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(
                new String[] { "Product Name", "Product Description", "Product Price", "Stock Available", "Actions" });
        for (Product product : products) {
            tableModel.addRow(
                    new Object[] { product.getName(), product.getDescription(),
                            "$" + String.format("%.2f", product.getPrice()),
                            String.valueOf(product.getStockQuantity()), "+" });
        }

        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(600, 750));
        table.setFillsViewportHeight(true);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        scrollPane.setViewportView(table);
    }

    public void displayProducts() {
        String searchTerm = searchField.getText().toLowerCase();
        tableModel.setRowCount(0);

        for (Product product : products) {
            if (product.getName().toLowerCase().contains(searchTerm)) {
                tableModel.addRow(new Object[] { product.getName(), product.getDescription(),
                        String.format("%.2f", product.getPrice()),
                        String.valueOf(product.getStockQuantity()), "+" });
            }
        }
    }

}
