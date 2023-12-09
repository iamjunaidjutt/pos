package com.scd.GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.scd.Business.ManageCart;
import com.scd.Models.Item;
import com.scd.Models.User;

public class ViewCartGUI extends JFrame implements ActionListener {
    private User user;

    private JTable table;
    private DefaultTableModel tableModel;

    private JScrollPane scrollPane;

    private List<Item> items;

    private JTextField eCNField;

    private JButton pOButton;

    private JButton cOButton;

    private ManageCart manageCart;

    private double totalPrice = 0.0;

    public ViewCartGUI(User user) {
        super("View Cart");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        manageCart = new ManageCart();
        this.user = user;

        // Menu
        MenuBarGUI menuBarGUI = new MenuBarGUI(this, user);
        setJMenuBar(menuBarGUI);

        JPanel panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(1200, 50));
        panel1.setLayout(new FlowLayout());

        JLabel eCNLabel = new JLabel("Enter Customer Name: ");
        panel1.add(eCNLabel);

        eCNField = new JTextField(20);
        eCNField.setPreferredSize(new Dimension(200, 25));
        panel1.add(eCNField);

        pOButton = new JButton("Place Order");
        panel1.add(pOButton);
        pOButton.addActionListener(this);

        cOButton = new JButton("Cancel Order");
        panel1.add(cOButton);
        cOButton.addActionListener(this);

        add(panel1);
        displayCartItems();

        setVisible(true);
    }

    public void displayCartItems() {
        items = manageCart.getCartItems();
        totalPrice = manageCart.getCartTotal();

        if (items == null || items.isEmpty()) {
            getContentPane().removeAll();
            JLabel emptyCartLabel = new JLabel("Your cart is empty");
            emptyCartLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            add(emptyCartLabel);

            JButton shopNowButton = new JButton("Shop Now");
            shopNowButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            shopNowButton.setFocusable(false);
            shopNowButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                    new ListAllProductsGUI(user);
                }
            });
            add(shopNowButton);
        } else {
            JPanel panel2 = new JPanel();
            panel2.setPreferredSize(new Dimension(1200, 750));

            tableModel = new DefaultTableModel();
            tableModel.setColumnIdentifiers(
                    new String[] { "Item Code", "Product Name", "Price", "Quantity Ordered", "Total Price", "Edit",
                            "Remove" });
            for (Item item : items) {
                tableModel.addRow(
                        new Object[] { item.getCode(), item.getProduct().getName(),
                                "$" + String.format("%.2f", item.getProduct().getPrice()),
                                item.getQuantityOrdered(), "$" + String.format("%.2f", item.getPrice()), "[Edit]",
                                "X" });
            }

            tableModel.addRow(
                    new Object[] { "", "", "", "Total Price:", "$" + String.format("%.2f", totalPrice), "", "" });

            table = new JTable(tableModel);
            table.setPreferredScrollableViewportSize(new Dimension(1200, 750));
            table.setFillsViewportHeight(true);
            table.getColumnModel().getColumn(0).setPreferredWidth(100);
            table.getColumnModel().getColumn(1).setPreferredWidth(400);
            table.getColumnModel().getColumn(2).setPreferredWidth(100);
            table.getColumnModel().getColumn(3).setPreferredWidth(100);
            table.getColumnModel().getColumn(4).setPreferredWidth(100);
            table.getColumnModel().getColumn(5).setPreferredWidth(100);
            table.getColumnModel().getColumn(6).setPreferredWidth(100);

            // set items alignment in center of the cell
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
                    if (selectedColumn == 5) {
                        try {
                            int quantityOrdered = Integer.parseInt(JOptionPane.showInputDialog("Enter new quantity: "));
                            int itemCode = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
                            try {
                                manageCart.updateItemQuantity(itemCode, quantityOrdered);
                                JOptionPane.showMessageDialog(null, "Quantity updated successfully");
                                updateCartItems();
                            } catch (Exception e2) {
                                JOptionPane.showMessageDialog(null, "Error updating quantity");
                            }
                            selectedColumn = -1;
                        } catch (Exception e2) {
                            JOptionPane.showMessageDialog(null, "Error updating quantity");
                        }
                    } else if (selectedColumn == 6) {
                        try {
                            int itemCode = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
                            // First Confirm delete and then delete
                            if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?",
                                    "Confirm Delete", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                                try {
                                    manageCart.removeItemFromCart(itemCode);
                                    JOptionPane.showMessageDialog(null, "Item removed successfully");
                                    updateCartItems();
                                } catch (Exception e2) {
                                    JOptionPane.showMessageDialog(null, "Error removing item");
                                }
                            }
                            selectedColumn = -1;
                        } catch (Exception e2) {
                            JOptionPane.showMessageDialog(null, "Error removing item");
                        }
                    }
                }
            });

            panel2.add(scrollPane);

            add(panel2);

            revalidate();
        }

    }

    public void updateCartItems() {
        items = manageCart.getCartItems();
        totalPrice = manageCart.getCartTotal();

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(
                new String[] { "Item Code", "Product Name", "Price", "Quantity Ordered", "Total Price", "Edit",
                        "Remove" });
        for (Item item : items) {
            tableModel.addRow(
                    new Object[] { item.getCode(), item.getProduct().getName(),
                            "$" + String.format("%.2f", item.getProduct().getPrice()),
                            item.getQuantityOrdered(), "$" + String.format("%.2f", item.getPrice()), "[Edit]", "X" });
        }

        tableModel.addRow(new Object[] { "", "", "", "Total Price:", "$" + String.format("%.2f", totalPrice), "", "" });

        table.setModel(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(1200, 750));
        table.setFillsViewportHeight(true);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(400);
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

        scrollPane.setViewportView(table);
    }

    /**
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pOButton) {
            String customerName = eCNField.getText();
            if (customerName != null) {
                if (customerName.trim().length() > 0) {
                    if (manageCart.placeOrder(customerName)) {
                        JOptionPane.showMessageDialog(null, "Order placed successfully");
                        eCNField.setText("");
                        updateCartItems();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error placing order");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Customer name cannot be empty");
                }
            }
        } else if (e.getSource() == cOButton) {
            try {
                if (manageCart.clearCart()) {
                    JOptionPane.showMessageDialog(null, "Order cancelled successfully");
                    updateCartItems();
                } else {
                    JOptionPane.showMessageDialog(null, "Error cancelling order");
                }
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, "Error cancelling order");
            }
        }
    }
}
