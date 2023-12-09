package com.scd.Data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import com.scd.Helper.FactoryProvider;
import com.scd.Models.Category;
import com.scd.Models.Product;

public class CategoryDAO implements DAO {

    /**
     * @return EntityManager
     */
    public EntityManager getEntityManager() {
        return ((EntityManagerFactory) FactoryProvider.getFactory()).createEntityManager();
    }

    @Override
    public boolean save(Object obj) {
        Category category = (Category) obj;
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(category);
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
            List<Object> categories = entityManager.createQuery("from Category", Object.class).getResultList();
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

    public Category getById(int id) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Category category = entityManager.find(Category.class, id);
            transaction.commit();
            return category;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive() && transaction != null)
                transaction.rollback();
            return null;
        } finally {
            entityManager.close();
        }
    }

    public Category getByName(String name) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Category category = entityManager.createQuery("from Category where name = :name", Category.class)
                    .setParameter("name", name).getSingleResult();
            transaction.commit();
            return category;
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
        Category category = (Category) obj;
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Category category2 = entityManager.find(Category.class, category.getCode());
            category2.setName(category.getName());
            category2.setDescription(category.getDescription());
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
            Category category = entityManager.find(Category.class, id);
            entityManager.remove(category);
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

    // public boolean addSubCategory(Category category, Category subCategory) {
    // EntityManager entityManager = getEntityManager();
    // EntityTransaction transaction = entityManager.getTransaction();
    // try {
    // transaction.begin();
    // Category category2 = entityManager.find(Category.class, category.getCode());
    // try {
    // entityManager.persist(subCategory);
    // } catch (PersistenceException e) {
    // e.printStackTrace();
    // entityManager.merge(subCategory);
    // category2.getSubcategories().add(subCategory);
    // }
    // category2.getSubcategories().add(subCategory);
    // entityManager.merge(category2);
    // transaction.commit();
    // return true;
    // } catch (Exception e) {
    // e.printStackTrace();
    // if (transaction.isActive() && transaction != null)
    // transaction.rollback();
    // return false;
    // } finally {
    // entityManager.close();
    // }
    // }

    public boolean addSubCategory(int parent_category_id, Category subCategory) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Category category2 = entityManager.find(Category.class, parent_category_id);
            try {
                entityManager.persist(subCategory);
                category2.getSubcategories().add(subCategory);
                System.out.println("Category: " + category2);
                for (Category category : category2.getSubcategories()) {
                    System.out.println("Subcategory: " + category);
                }
                entityManager.merge(category2);
                transaction.commit();
                return true;
            } catch (PersistenceException e) {
                e.printStackTrace();
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

    public List<Category> getSubCategories(Category category) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            System.out.println("--> Category: " + category);
            transaction.begin();
            Category category2 = entityManager.find(Category.class, category.getCode());
            List<Category> subCategories = category2.getSubcategories();
            System.out.println(subCategories);
            transaction.commit();
            if (subCategories == null)
                return new ArrayList<Category>();
            return subCategories;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive() && transaction != null)
                transaction.rollback();
            return null;
        } finally {
            entityManager.close();
        }
    }

    public boolean deleteSubCategory(Category category, Category subCategory) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Category category2 = entityManager.find(Category.class, category.getCode());
            Category subCategory2 = entityManager.find(Category.class, subCategory.getCode());
            category2.getSubcategories().remove(subCategory2);
            entityManager.merge(category2);
            entityManager.remove(subCategory2);
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

    public void deleteProductFromCategories(Product product) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            List<Object> categories = getAll();
            List<Category> categories2 = new ArrayList<>();
            for (Object object : categories) {
                categories2.add((Category) object);
            }
            for (Category category : categories2) {
                category.getProducts().remove(product);
                entityManager.merge(category);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive() && transaction != null)
                transaction.rollback();
        } finally {
            entityManager.close();
        }
    }

}
