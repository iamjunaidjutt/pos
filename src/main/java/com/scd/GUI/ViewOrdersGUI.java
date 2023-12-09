package com.scd.GUI;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.scd.Business.ManageCart;
import com.scd.Models.Orders;
import com.scd.Models.User;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewOrdersGUI extends JFrame implements ActionListener {
    private User user;
    private JLabel titleLabel;

    private JTextField searchField;

    private JPanel panel1;
    private JPanel panel2;

    private JTable table;

    private DefaultTableModel tableModel;

    private JScrollPane scrollPane;

    private List<Orders> orders;

    private JLabel idLabel;
    private JLabel statusLabel;

    private JTextField idField;

    private JComboBox<String> statusComboBox;

    private JButton updateButton;

    private JPanel panel3;

    private ManageCart manageCart;

    int orderId = -1;
    String status = "";

    public ViewOrdersGUI(User user) {
        super("View Orders");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Initialize ManageCart
        manageCart = new ManageCart();
        this.user = user;

        // Menu Bar
        MenuBarGUI menuBarGUI = new MenuBarGUI(this, user);
        setJMenuBar(menuBarGUI);

        // Title
        titleLabel = new JLabel("Search for an order");

        // Search Field
        searchField = new JTextField(20);
        searchField.setPreferredSize(new Dimension(200, 30));

        // Panels
        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.setPreferredSize(new Dimension(1200, 50));

        idLabel = new JLabel("Order Id");
        idField = new JTextField(10);
        idField.setPreferredSize(new Dimension(100, 30));

        statusLabel = new JLabel("Status");

        String data[] = { "Pending", "Paid", "Cancel" };
        statusComboBox = new JComboBox<>(data);

        updateButton = new JButton("Update");
        updateButton.addActionListener(this);

        panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());
        panel3.setPreferredSize(new Dimension(1200, 50));

        panel3.add(idLabel);
        panel3.add(idField);
        panel3.add(statusLabel);
        panel3.add(statusComboBox);
        panel3.add(updateButton);

        panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(1200, 700));
        panel2.setLayout(new GridLayout(1, 1));

        panel1.add(titleLabel);
        panel1.add(searchField);

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                displayOrdersSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                displayOrdersSearch();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Plain text components do not fire these events
            }
        });

        panel2.add(displayOrders());
        add(panel1);
        add(panel3);
        add(panel2);

        setVisible(true);
    }

    /**
     * @return JScrollPane
     */
    public JScrollPane displayOrders() {
        orders = manageCart.getOrders();

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(
                new String[] { "Order Id", "Customer Name", "Order Date", "Total Items", "Total Amount", "Status",
                        "Actions" });
        for (Orders order : orders) {
            tableModel.addRow(new Object[] { order.getId(), order.getCustomer().getName(), order.getDate(),
                    order.getItems().size(), "$" + String.format("%.2f", order.getTotal()), order.getStatus(),
                    "[view]" });
        }

        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(1200, 750));
        table.setFillsViewportHeight(true);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        table.getColumnModel().getColumn(6).setPreferredWidth(100);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);

        scrollPane = new JScrollPane(table);

        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = table.getSelectedRow();
                int selectedColumn = table.getSelectedColumn();
                if (selectedRow != -1 && selectedColumn == 6) {
                    int orderId = (int) table.getValueAt(selectedRow, 0);
                    Orders order = manageCart.getOrderDetails(orderId);
                    System.out.println(order);
                    new OrderDetailsGUI(order);
                } else if (selectedRow != -1 && selectedColumn != 6) {
                    int orderId = (int) table.getValueAt(selectedRow, 0);
                    Orders order = manageCart.getOrderDetails(orderId);
                    System.out.println(order);
                    idField.setText(String.valueOf(order.getId()));
                    statusComboBox.setSelectedItem(order.getStatus());
                }
            }
        });

        return scrollPane;
    }

    public void updateOrders() {
        orders = manageCart.getOrders();
        tableModel.setRowCount(0);
        for (Orders order : orders) {
            tableModel.addRow(new Object[] { order.getId(), order.getCustomer().getName(), order.getDate(),
                    order.getItems().size(), "$" + String.format("%.2f", order.getTotal()), order.getStatus(),
                    "[view]" });
        }

        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(1200, 750));
        table.setFillsViewportHeight(true);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        table.getColumnModel().getColumn(6).setPreferredWidth(100);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        scrollPane.setViewportView(table);
    }

    public void displayOrdersSearch() {
        String searchTerm = searchField.getText().toLowerCase();
        tableModel.setRowCount(0);

        for (Orders order : orders) {
            if (order.getCustomer().getName().toLowerCase().contains(searchTerm)) {
                tableModel.addRow(new Object[] { order.getId(), order.getCustomer().getName(), order.getDate(),
                        order.getItems().size(), "$" + String.format("%.2f", order.getTotal()), order.getStatus(),
                        "[view]" });
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            if (idField.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please select an order");
                return;
            }
            orderId = Integer.parseInt(idField.getText());
            status = (String) statusComboBox.getSelectedItem();
            try {
                if (status.equals("Pending")) {
                    manageCart.updateOrderStatus(orderId, status);
                    updateOrders();
                    JOptionPane.showMessageDialog(this, "Order status updated");
                } else if (status.equals("Paid")) {
                    manageCart.updateOrderStatus(orderId, status);
                    updateOrders();
                    JOptionPane.showMessageDialog(this, "Order status updated");
                } else if (status.equals("Cancel")) {
                    manageCart.cancelOrder(orderId);
                    updateOrders();
                    JOptionPane.showMessageDialog(this, "Order status updated");
                }
                idField.setText("");
                statusComboBox.setSelectedIndex(0);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating order status");
            }
        }
    }

}
