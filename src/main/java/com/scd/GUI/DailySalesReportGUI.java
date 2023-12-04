package com.scd.GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
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
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;

import com.scd.Business.DailySalesReport;
import com.scd.Business.GenerateInvoice;
import com.scd.Business.ManageCart;
import com.scd.Models.Category;
import com.scd.Models.CategorySales;
import com.scd.Models.Item;
import com.scd.Models.Orders;

public class DailySalesReportGUI extends JFrame implements ActionListener {
    private JLabel titleLabel, companyLabel, dateLabel, totalTitleLabel, totalItemLabel,
            grandAmountLabel, statusLabel, tyLabel;

    private JPanel panel1, panel2, panel3;

    private JTable table;

    private DefaultTableModel tableModel;

    private JScrollPane scrollPane;

    private JButton exportButton;

    private JButton refreshButton;

    private JSpinner toDateSpinner;
    private JSpinner fromDateSpinner;

    private List<CategorySales> categoriesSales;

    private LocalDateTime convertToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public DailySalesReportGUI() {
        super("Daily Sales Report");
        setSize(700, 900);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        titleLabel = new JLabel("Daily Sales Report");
        titleLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        add(titleLabel);

        companyLabel = new JLabel("POS-Pharmacy");
        companyLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        companyLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        add(companyLabel);

        add(Box.createVerticalStrut(10));

        toDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(toDateSpinner, "dd.MM.yyyy");
        toDateSpinner.setEditor(dateEditor);

        fromDateSpinner = new JSpinner(new SpinnerDateModel());
        dateEditor = new JSpinner.DateEditor(fromDateSpinner, "dd.MM.yyyy");
        fromDateSpinner.setEditor(dateEditor);

        refreshButton = new JButton("Refresh");
        refreshButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        refreshButton.setFocusable(false);

        dateLabel = new JLabel("Date: " + java.time.LocalDate.now());
        dateLabel.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        dateLabel.setFont(new Font("Arial", Font.BOLD, 15));

        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel1.setMaximumSize(new Dimension(500, 100));
        panel1.add(new JLabel("To: "));
        panel1.add(toDateSpinner);
        panel1.add(new JLabel("From: "));
        panel1.add(fromDateSpinner);
        panel1.add(refreshButton);
        panel1.add(dateLabel);
        add(panel1);

        add(Box.createVerticalStrut(10));

        displayCategorySales();

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

        int totalItems = 0;
        for (CategorySales categorySales : categoriesSales) {
            totalItems += categorySales.getQuantity();
        }

        totalItemLabel = new JLabel("Total Items: " + totalItems);
        // totalItemLabel.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        totalItemLabel.setFont(new Font("Arial", Font.BOLD, 15));
        panel3.add(totalItemLabel);

        double grandTotal = 0;
        for (CategorySales categorySales : categoriesSales) {
            grandTotal += categorySales.getTotal();
        }

        grandAmountLabel = new JLabel("Grand Total: " + String.format("%.2f", grandTotal));
        // grandAmountLabel.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        grandAmountLabel.setFont(new Font("Arial", Font.BOLD, 15));
        panel3.add(grandAmountLabel);

        // statusLabel = new JLabel("Status: " + order.getStatus());
        // // statusLabel.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        // statusLabel.setFont(new Font("Arial", Font.BOLD, 15));
        // panel3.add(statusLabel);

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

    public void displayCategorySales() {
        DailySalesReport dailySalesReport = new DailySalesReport();
        categoriesSales = dailySalesReport.getCategoriesSales(convertToLocalDateTime((Date) fromDateSpinner.getValue()),
                convertToLocalDateTime((Date) toDateSpinner.getValue()));

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[] { "Category", "Quantity", "Total" });

        for (CategorySales categorySales : categoriesSales) {
            tableModel.addRow(new Object[] { categorySales.getName(), categorySales.getQuantity(),
                    String.format("%.2f", categorySales.getTotal()) });
        }

        table = new JTable(tableModel);

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        add(scrollPane);
    }

    public void updateCategorySales() {
        DailySalesReport dailySalesReport = new DailySalesReport();
        categoriesSales = dailySalesReport.getCategoriesSales(convertToLocalDateTime((Date) fromDateSpinner.getValue()),
                convertToLocalDateTime((Date) toDateSpinner.getValue()));

        for (int i = 0; i < categoriesSales.size(); i++) {
            tableModel.setValueAt(categoriesSales.get(i).getQuantity(), i, 1);
            tableModel.setValueAt(String.format("%.2f", categoriesSales.get(i).getTotal()), i, 2);
        }

        int totalItems = 0;
        for (CategorySales categorySales : categoriesSales) {
            totalItems += categorySales.getQuantity();
        }

        totalItemLabel.setText("Total Items: " + totalItems);

        double grandTotal = 0;
        for (CategorySales categorySales : categoriesSales) {
            grandTotal += categorySales.getTotal();
        }

        grandAmountLabel.setText("Grand Total: " + String.format("%.2f", grandTotal));
    }

    // public void displayProducts() {
    // List<Item> items = order.getItems();

    // tableModel = new DefaultTableModel();
    // tableModel.setColumnIdentifiers(new String[] { "Code", "Product Name",
    // "Price", "Quantity", "Total" });

    // for (Item item : items) {
    // tableModel.addRow(new Object[] { item.getProduct().getCode(),
    // item.getProduct().getName(),
    // String.format("%.2f", item.getProduct().getPrice()),
    // item.getQuantityOrdered(),
    // String.format("%.2f", item.getPrice()) });
    // }

    // table = new JTable(tableModel);

    // scrollPane = new JScrollPane(table);
    // scrollPane.setPreferredSize(new Dimension(500, 300));
    // add(scrollPane);
    // }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == refreshButton) {

        }
    }

    public static void main(String[] args) {
        new DailySalesReportGUI();
    }

}
