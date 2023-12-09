package com.scd.Helper;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactoryProvider {
    public static javax.persistence.EntityManagerFactory emf;

    /**
     * @return EntityManagerFactory
     */
    public static EntityManagerFactory getFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("pu");
        }
        return emf;
    }

    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
