package com.scd;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.scd.Models.User;

/**
 * POS-Pharmacy
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("POS-Pharmacy");
        System.out.println("Hey committing now!");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        User user = new User();
        user.setUsername("dummy2");
        user.setPassword("dummy123");

        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.clear();
        emf.close();
    }
}
