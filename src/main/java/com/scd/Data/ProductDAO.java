package com.scd.Data;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.scd.Models.Product;

public class ProductDAO implements DAO {
    private EntityManagerFactory emf;

    public ProductDAO(EntityManagerFactory emf) {
        emf = Persistence.createEntityManagerFactory("pu");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public boolean create(Object obj) {
        Product product = (Product) obj;
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            if (em != null && em.isOpen())
                em.close();
        }
    }

    @Override
    public List<Object> read() {
        List<Object> products = null;
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            products = em.createQuery("SELECT p FROM Product p", Object.class).getResultList();
            em.getTransaction().commit();
            return products;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return products;
        } finally {
            if (em != null && em.isOpen())
                em.close();
        }
    }

    @Override
    public boolean update(Object obj) {
        Product product = (Product) obj;
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(product);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            if (em != null && em.isOpen())
                em.close();
        }
    }

    @Override
    public boolean delete(UUID id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Product product = em.find(Product.class, id);
            em.remove(product);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            if (em != null && em.isOpen())
                em.close();
        }
    }

}
