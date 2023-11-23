package com.scd.Data;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.scd.Models.Item;

public class ItemDAO implements DAO {
    private EntityManagerFactory emf;

    public ItemDAO() {
        emf = Persistence.createEntityManagerFactory("pu");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public boolean create(Object obj) {
        Item item = (Item) obj;
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(item);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error creating item: " + e.getMessage());
            return false;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<Object> read() {
        List<Object> items = null;
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            items = em.createQuery("SELECT i FROM Item i", Object.class).getResultList();
            em.getTransaction().commit();
            return items;
        } catch (Exception e) {
            System.out.println("Error reading items: " + e.getMessage());
            return items;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public boolean update(Object obj) {
        Item item = (Item) obj;
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(item);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error updating item: " + e.getMessage());
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
            em.remove(em.find(Item.class, id));
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error deleting item: " + e.getMessage());
            return false;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

}
