package com.scd.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class NewItemGUI extends JFrame implements ActionListener {

    public NewItemGUI() {
        super("Add New Item");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Menu Bar
        MenuBarGUI menuBarGUI = new MenuBarGUI(this);
        setJMenuBar(menuBarGUI);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    public static void main(String[] args) {
        NewItemGUI newItemGUI = new NewItemGUI();
        newItemGUI.setVisible(true);
    }

}
