package com.scd.Data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.scd.Helper.FactoryProvider;
import com.scd.Models.Category;
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

            // Clear categories from product to avoid duplicates
            List<Category> categories = new ArrayList<>(product.getCategories());
            product.getCategories().clear();

            // Save the product
            entityManager.persist(product);

            // Reattach categories
            for (Category category : categories) {
                // Check if the category is managed
                if (!entityManager.contains(category)) {
                    // If not, merge it
                    category = entityManager.merge(category);
                }

                // Update the relationship on both sides
                category.getProducts().add(product);
                product.getCategories().add(category);

                // No need to merge category again, as it's already managed
            }

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
            // Clear categories from product to avoid duplicates
            List<Category> categories = new ArrayList<>(product.getCategories());
            product.getCategories().clear();

            // Merge the product
            product = entityManager.merge(product);

            // Reattach categories
            for (Category category : categories) {
                // Check if the category is managed
                if (!entityManager.contains(category)) {
                    // If not, merge it
                    category = entityManager.merge(category);
                }

                // Update the relationship on both sides
                category.getProducts().add(product);
                product.getCategories().add(category);

                // No need to merge category again, as it's already managed
            }

            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null && transaction.isActive())
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
            System.out.println("Product found: " + product);

            if (product != null) {
                product.getCategories().clear();

                System.out.println("Categories cleared");

                CategoryDAO categoryDAO = new CategoryDAO();
                categoryDAO.deleteProductFromCategories(product);

                entityManager.remove(product);
                System.out.println("Product removed");

                transaction.commit();
                return true;
            } else {
                System.out.println("Product not found for id: " + id);
                return false;
            }
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

    public Product getByName(String name) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Product product = entityManager.createQuery("from Product where name = :name", Product.class)
                    .setParameter("name", name).getSingleResult();
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

    public List<Product> getProductsByCategory(int id) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            List<Product> products = entityManager.createQuery("from Product where category_id = :id", Product.class)
                    .setParameter("id", id).getResultList();
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

    public List<Category> getCategoriesByProduct(int id) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            List<Category> categories = entityManager
                    .createQuery("select categories from Product where id = :id", Category.class)
                    .setParameter("id", id).getResultList();
            transaction.commit();
            return categories;
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
