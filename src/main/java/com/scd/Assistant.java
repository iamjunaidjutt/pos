package com.scd;

import javax.persistence.*;

@Entity
public class Assistant extends Role {
    // private static String ASROLE = "Assistant"; //can be used later

    @Override
    public void save() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("scd_project");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            em.persist(this);

            transaction.commit();
            System.out.println("Assistant saved");
        } 
        catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } 
        finally {
            em.close();
            emf.close();
        }
    }

    @Override
    public boolean login(String username, String password) {
        // Implement login for Assistant using JPA
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("scd_project");
        EntityManager em = emf.createEntityManager();

        try {
            // Use JPA to query the database for the user with the given credentials
            Assistant assistant = em.createQuery("SELECT a FROM Assistant a WHERE a.username = :username AND a.password = :password", Assistant.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();

            return assistant != null;
        } 
        catch (NoResultException e) {
            return false; // No matching user found
        } 
        finally {
            em.close();
            emf.close();
        }
    }
}
