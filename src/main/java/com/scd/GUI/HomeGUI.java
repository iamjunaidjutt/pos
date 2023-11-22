package com.scd.GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeGUI extends JFrame implements ActionListener {
    private JLabel titleLabel;

    private JButton loginButton;
    private JButton registerButton;

    public HomeGUI() {
        super("POS-Pharmacy");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        titleLabel = new JLabel("POS-Pharmacy");
        titleLabel.setFont(titleLabel.getFont().deriveFont(32.0f));
        titleLabel.setBounds(485, 250, 400, 50);
        add(titleLabel);

        loginButton = new JButton("Login");
        loginButton.setBounds(500, 350, 200, 50);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
        add(loginButton);

        registerButton = new JButton("Register");
        registerButton.setBounds(500, 420, 200, 50);
        registerButton.setFocusable(false);
        registerButton.addActionListener(this);
        add(registerButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            LoginGUI loginGUI = new LoginGUI();
            loginGUI.setVisible(true);
            setVisible(false);
            // dispose();
        } else if (e.getSource() == registerButton) {
            RegisterGUI registerGUI = new RegisterGUI();
            registerGUI.setVisible(true);
            setVisible(false);
            // dispose();
        }
    }
}
