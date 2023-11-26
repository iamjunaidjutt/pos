package com.scd.Data;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.scd.Helper.FactoryProvider;
import com.scd.Models.Item;
import com.scd.Models.Product;

public class ItemDAO implements DAO {
    public EntityManager getEntityManager() {
        return ((EntityManagerFactory) FactoryProvider.getFactory()).createEntityManager();
    }

    @Override
    public boolean save(Object obj) {
        Item item = (Item) obj;
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(item);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (entityManager.getTransaction().isActive() && entityManager.getTransaction() != null)
                entityManager.getTransaction().rollback();
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Object> getAll() {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Object> items = entityManager.createQuery("from Item", Object.class)
                    .getResultList();
            entityManager.getTransaction().commit();
            return items;
        } catch (Exception e) {
            e.printStackTrace();
            if (entityManager.getTransaction().isActive() && entityManager.getTransaction() != null)
                entityManager.getTransaction().rollback();
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean update(Object obj) {
        Item item = (Item) obj;
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(item);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (entityManager.getTransaction().isActive() && entityManager.getTransaction() != null)
                entityManager.getTransaction().rollback();
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean delete(int id) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Item item = entityManager.find(Item.class, id);
            entityManager.remove(item);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive() && transaction != null)
                transaction.rollback();
            return false;
        } finally {
            entityManager.close();
        }
    }

    public Item getById(int id) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Item item = entityManager.find(Item.class, id);
            transaction.commit();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive() && transaction != null)
                transaction.rollback();
            return null;
        } finally {
            entityManager.close();
        }
    }

    public boolean saveItemWithProduct(Item item, Product detacheProduct) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Product product = entityManager.find(Product.class, detacheProduct.getCode());
            item.setProduct(product);
            entityManager.persist(item);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive() && transaction != null)
                transaction.rollback();
            return false;
        } finally {
            entityManager.close();
        }
    }

}
