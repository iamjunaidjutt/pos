package com.scd.GUI;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Dimension;
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

        // Using a single BoxLayout for the content pane
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        titleLabel = new JLabel("POS-Pharmacy");
        titleLabel.setFont(titleLabel.getFont().deriveFont(32.0f));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);

        loginButton = new JButton("Login");
        loginButton.setAlignmentX(CENTER_ALIGNMENT);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        registerButton = new JButton("Register");
        registerButton.setAlignmentX(CENTER_ALIGNMENT);
        registerButton.setFocusable(false);
        registerButton.addActionListener(this);

        // Set preferred and maximum size for the buttons
        Dimension buttonSize = new Dimension(200, 50);
        loginButton.setPreferredSize(buttonSize);
        registerButton.setPreferredSize(buttonSize);

        loginButton.setMaximumSize(buttonSize);
        registerButton.setMaximumSize(buttonSize);

        add(Box.createVerticalGlue());
        add(titleLabel);
        add(Box.createVerticalStrut(40));
        add(loginButton);
        add(Box.createVerticalStrut(20));
        add(registerButton);
        add(Box.createVerticalGlue());

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
