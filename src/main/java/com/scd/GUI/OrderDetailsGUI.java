package com.scd.GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.scd.Business.GenerateInvoice;
import com.scd.Models.Item;
import com.scd.Models.Orders;

public class OrderDetailsGUI extends JFrame implements ActionListener {
    private JLabel titleLabel, companyLabel, invoiceLabel, dateLabel, customerLabel, totalTitleLabel, totalItemLabel,
            grandAmountLabel, statusLabel, tyLabel;

    private JPanel panel1, panel2, panel3;

    private JTable table;

    private DefaultTableModel tableModel;

    private JScrollPane scrollPane;

    private Orders order;

    private JButton exportButton;

    public OrderDetailsGUI(Orders order) {
        super("Order Details");
        setSize(500, 600);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        this.order = order;

        titleLabel = new JLabel("Invoice");
        titleLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        add(titleLabel);

        companyLabel = new JLabel("POS-Pharmacy");
        companyLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        companyLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        add(companyLabel);

        add(Box.createVerticalStrut(10));

        invoiceLabel = new JLabel("Invoice No. " + order.getId());
        invoiceLabel.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        invoiceLabel.setFont(new Font("Arial", Font.BOLD, 15));

        dateLabel = new JLabel("Date: " + java.time.LocalDate.now());
        dateLabel.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        dateLabel.setFont(new Font("Arial", Font.BOLD, 15));

        customerLabel = new JLabel("Customer Name: " + order.getCustomer().getName());
        customerLabel.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        customerLabel.setFont(new Font("Arial", Font.BOLD, 15));

        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(2, 2));
        panel1.setMaximumSize(new Dimension(500, 60));
        panel1.add(invoiceLabel);
        panel1.add(dateLabel);
        panel1.add(customerLabel);
        add(panel1);

        add(Box.createVerticalStrut(10));

        displayOrders();

        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 2));
        panel2.setMaximumSize(new Dimension(500, 100));

        panel2.add(new JPanel());

        panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        panel3.setMaximumSize(new Dimension(250, 100));

        totalTitleLabel = new JLabel("Total Purchase");
        // totalTitleLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        totalTitleLabel.setFont(new Font("Arial", Font.BOLD, 15));
        panel3.add(totalTitleLabel);

        totalItemLabel = new JLabel("Total Items: " + order.getItems().size());
        // totalItemLabel.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        totalItemLabel.setFont(new Font("Arial", Font.BOLD, 15));
        panel3.add(totalItemLabel);

        grandAmountLabel = new JLabel("Grand Total: " + String.format("%.2f", order.getTotal()));
        // grandAmountLabel.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        grandAmountLabel.setFont(new Font("Arial", Font.BOLD, 15));
        panel3.add(grandAmountLabel);

        statusLabel = new JLabel("Status: " + order.getStatus());
        // statusLabel.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 15));
        panel3.add(statusLabel);

        panel2.add(panel3);

        add(panel2);

        tyLabel = new JLabel("Thank you for shopping with us!");
        tyLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        tyLabel.setFont(new Font("Arial", Font.BOLD, 15));
        add(tyLabel);

        add(Box.createVerticalStrut(10));

        exportButton = new JButton("Export");
        exportButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        exportButton.setFocusable(false);
        exportButton.addActionListener(this);
        add(exportButton);

        add(Box.createVerticalStrut(10));

        setVisible(true);
    }

    public void displayOrders() {
        List<Item> items = order.getItems();

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[] { "Code", "Product Name", "Price", "Quantity", "Total" });

        for (Item item : items) {
            tableModel.addRow(new Object[] { item.getProduct().getCode(), item.getProduct().getName(),
                    String.format("%.2f", item.getProduct().getPrice()), item.getQuantityOrdered(),
                    String.format("%.2f", item.getPrice()) });
        }

        table = new JTable(tableModel);

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        add(scrollPane);
    }

    /**
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exportButton) {
            GenerateInvoice generateInvoice = new GenerateInvoice(order);
            if (generateInvoice.generate()) {
                JOptionPane.showMessageDialog(this, "Invoice exported successfully!");
                dispose();
            } else
                JOptionPane.showMessageDialog(this, "Error exporting invoice!");
        }
    }

}
