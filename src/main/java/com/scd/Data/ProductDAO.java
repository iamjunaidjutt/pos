package com.scd.Data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.scd.Helper.FactoryProvider;
import com.scd.Models.Product;

public class ProductDAO implements DAO {
    public EntityManager getEntityManager() {
        return ((EntityManagerFactory) FactoryProvider.getFactory()).createEntityManager();
    }

    @Override
    public boolean save(Object obj) {
        Product product = (Product) obj;
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(product);
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

    @Override
    public List<Object> getAll() {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            List<Object> products = entityManager.createQuery("from Product", Object.class).getResultList();
            transaction.commit();
            return products;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive() && transaction != null)
                transaction.rollback();
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean update(Object obj) {
        Product product = (Product) obj;
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(product);
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

    @Override
    public boolean delete(int id) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Product product = entityManager.find(Product.class, id);
            entityManager.remove(product);
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

    public Product getById(int id) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Product product = entityManager.find(Product.class, id);
            transaction.commit();
            return product;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive() && transaction != null)
                transaction.rollback();
            return null;
        } finally {
            entityManager.close();
        }
    }

}
