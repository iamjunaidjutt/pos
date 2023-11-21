package com.scd;

import javax.persistence.*;

@Entity
public class Manager extends Role {
    // private static String MANROLE = "Manager";  //can be used later

    @Override
    public void save() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("scd_project");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(this);

            transaction.commit();
            System.out.println("Manager saved");
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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("scd_project");
        EntityManager em = emf.createEntityManager();

        try {
            Manager manager = em.createQuery("SELECT m FROM Manager m WHERE m.username = :username AND m.password = :password", Manager.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();

            return manager != null;
        } 
        catch (NoResultException e) {
            return false; //if we find no one 
        } 
        finally {
            em.close();
            emf.close();
        }
    }
}
