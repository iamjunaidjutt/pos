package com.scd.Data;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.scd.Models.Category;

public class CategoryDAO implements DAO {
    private EntityManagerFactory emf;

    public CategoryDAO() {
        emf = Persistence.createEntityManagerFactory("pu");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public boolean create(Object obj) {
        Category category = (Category) obj;
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(category);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error creating category: " + e.getMessage());
            return false;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<Object> read() {
        List<Object> categories = null;
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            categories = em.createQuery("SELECT c FROM Category c", Object.class).getResultList();
            em.getTransaction().commit();
            return categories;
        } catch (Exception e) {
            System.out.println("Error reading categories: " + e.getMessage());
            return categories;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public boolean update(Object obj) {
        Category category = (Category) obj;
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(category);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error updating category: " + e.getMessage());
            return false;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public boolean delete(UUID id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.find(Category.class, id));
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error deleting category: " + e.getMessage());
            return false;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

}
