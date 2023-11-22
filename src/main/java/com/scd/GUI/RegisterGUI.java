package com.scd.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import com.scd.Business.Manager;
import com.scd.Business.Register;
import com.scd.Business.SalesAssistant;
import com.scd.Models.Role;
import com.scd.Models.User;

public class RegisterGUI extends JFrame implements ActionListener {
    private JLabel titleLabel, usernameLabel, passwordLabel, roleLabel, loginLabel, loginLink;
    private JTextField userField;
    private JPasswordField passwordField;
    private JComboBox<String> comboBox;
    private JButton registerButton;
    private Register register;

    public RegisterGUI() {
        super("POS-Pharmacy");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        Font customFont = new Font("Arial", Font.PLAIN, 16);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        register = new Register();

        titleLabel = new JLabel("POS-Register");
        titleLabel.setFont(titleLabel.getFont().deriveFont(32.0f));
        titleLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        usernameLabel = new JLabel("Username");
        usernameLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        userField = new JTextField(30);
        userField.setMaximumSize(new Dimension(200, 40));
        userField.setFont(customFont);

        passwordLabel = new JLabel("Password");
        passwordLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        passwordField = new JPasswordField(30);
        passwordField.setMaximumSize(new Dimension(200, 40));
        passwordField.setFont(customFont);

        roleLabel = new JLabel("Role");
        roleLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        String[] roles = { "MANAGER", "SALES_ASSISTANT" };
        comboBox = new JComboBox<>(roles);
        comboBox.setMaximumSize(new Dimension(200, 40));
        comboBox.setFont(customFont);

        registerButton = new JButton("Register");
        registerButton.setFocusable(false);
        registerButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        registerButton.addActionListener(this);

        loginLabel = new JLabel("Already have an account?");
        loginLabel.setAlignmentX(CENTER_ALIGNMENT);

        loginLink = new JLabel("Login");
        loginLink.setForeground(Color.BLUE);
        loginLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Redirect to login form");
                LoginGUI loginGUI = new LoginGUI();
                loginGUI.setVisible(true);
                setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                loginLink.setForeground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginLink.setForeground(Color.BLUE);
            }
        });

        panel.add(Box.createVerticalGlue());
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(30));
        panel.add(usernameLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(userField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(passwordLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(passwordField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(roleLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(comboBox);
        panel.add(Box.createVerticalStrut(10));
        panel.add(registerButton);
        panel.add(Box.createVerticalStrut(10));
        JPanel loginPanel = new JPanel(new FlowLayout());
        loginPanel.add(loginLabel);
        loginPanel.add(loginLink);
        panel.add(loginPanel);
        panel.add(Box.createVerticalGlue());

        add(panel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            String username = userField.getText();
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);

            String rolee = (String) comboBox.getSelectedItem();

            if (username.equals("") || password.equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields");
                return;
            } else if (password.length() < 6) {
                JOptionPane.showMessageDialog(null, "Password must be at least 6 characters");
                return;
            }
            if (register.checkUsername(username)) {
                JOptionPane.showMessageDialog(null, "Username already exists");
                return;
            }

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            if ("MANAGER".equals(rolee)) {
                Role role = new Manager();
                user.setRole(role);
            } else if ("SALES_ASSISTANT".equals(rolee)) {
                Role role = new SalesAssistant();
                user.setRole(role);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid role");
                return;
            }

            if (register.register(user)) {
                JOptionPane.showMessageDialog(null, "Register successfully");
                setVisible(false);
                // dispose();
                LoginGUI loginGUI = new LoginGUI();
                loginGUI.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Register failed");
            }
        }
    }
}