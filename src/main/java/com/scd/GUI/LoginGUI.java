package com.scd.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.scd.Business.Login;
import com.scd.Models.User;

public class LoginGUI extends JFrame implements ActionListener {
    private JLabel titleLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel registerLabel;
    private JLabel registerLink;

    private JTextField usernameField;
    private JPasswordField passwordField;

    private JButton loginButton;

    private Login login;

    public LoginGUI() {
        super("POS-Pharmacy");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        Font customFont = new Font("Arial", Font.PLAIN, 16);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        login = new Login();

        titleLabel = new JLabel("POS-Login");
        titleLabel.setFont(titleLabel.getFont().deriveFont(32.0f));
        titleLabel.setBounds(485, 250, 400, 50);
        titleLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(500, 350, 200, 50);
        usernameLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        usernameField = new JTextField(30);
        usernameField.setBounds(500, 400, 200, 50);
        usernameField.setMaximumSize(new Dimension(200, 40));
        usernameField.setFont(customFont);
        usernameField.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(500, 450, 200, 50);
        passwordLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        passwordField = new JPasswordField(30);
        passwordField.setBounds(500, 500, 200, 50);
        passwordField.setMaximumSize(new Dimension(200, 40));
        passwordField.setFont(customFont);
        passwordField.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        loginButton = new JButton("Login");
        loginButton.setBounds(500, 600, 200, 50);
        loginButton.setFocusable(false);
        loginButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        loginButton.addActionListener(this);

        registerLabel = new JLabel("Don't have an account?");
        registerLabel.setAlignmentX(CENTER_ALIGNMENT);

        registerLink = new JLabel("Register");
        registerLink.setForeground(java.awt.Color.BLUE);
        registerLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Redirect to registration form");
                RegisterGUI registerGUI = new RegisterGUI();
                System.out.println("RegisterGUI created");
                registerGUI.setVisible(true);
                System.out.println("RegisterGUI made visible");
                setVisible(false);

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                registerLink.setForeground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                registerLink.setForeground(Color.BLUE);
            }
        });

        panel.add(Box.createVerticalGlue());
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(30));
        panel.add(usernameLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(usernameField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(passwordLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(passwordField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(loginButton);
        panel.add(Box.createVerticalStrut(10));
        JPanel registerPanel = new JPanel(new FlowLayout());
        registerPanel.add(registerLabel);
        registerPanel.add(registerLink);
        panel.add(registerPanel);
        panel.add(Box.createVerticalGlue());

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);

            if (login.login(username, password)) {
                // Dashboard dashboard = new Dashboard();
                // dashboard.setVisible(true);
                User user = login.getUser(username, password);
                new ListAllProductsGUI(user);
                setVisible(false);
            } else if (username.equals("") || password.equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password");
            }
        }
    }
}
