package com.scd;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.scd.GUI.HomeGUI;
import com.scd.Models.User;

/**
 * POS-Pharmacy
 *
 */
public class App {
    public static void main(String[] args) {
        HomeGUI homeGUI = new HomeGUI();
        homeGUI.setVisible(true);
    }
}
