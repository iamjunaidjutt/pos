package com.scd.Data;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.scd.Models.User;

public class UserDAO implements DAO {
    private EntityManagerFactory emf;

    public UserDAO() {
        emf = Persistence.createEntityManagerFactory("pu");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public boolean create(Object obj) {
        User user = (User) obj;
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error creating user: " + e.getMessage());
            return false;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<Object> read() {
        List<Object> users = null;
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            users = em.createQuery("SELECT u FROM User u", Object.class).getResultList();
            em.getTransaction().commit();
            return users;
        } catch (Exception e) {
            System.out.println("Error reading users: " + e.getMessage());
            return users;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public boolean update(Object obj) {
        User user = (User) obj;
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error updating user: " + e.getMessage());
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
            User user = em.find(User.class, id);
            if (user != null) {
                em.remove(user);
            }
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error deleting user: " + e.getMessage());
            return false;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public boolean userAuthenticate(String username, String password) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<User> query = em.createQuery(
                    "SELECT u FROM User u JOIN FETCH u.role WHERE u.username = :username AND u.password = :password",
                    User.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .setMaxResults(1);
            User user = query.getSingleResult();
            em.getTransaction().commit();

            return user != null;
        } catch (Exception e) {
            System.out.println("Error authenticating user: " + e.getMessage());
            return false;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public String userRole(String username, String password) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<User> query = em.createQuery(
                    "SELECT u FROM User u JOIN FETCH u.role WHERE u.username = :username AND u.password = :password",
                    User.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .setMaxResults(1);
            User user = query.getSingleResult();
            em.getTransaction().commit();
            return (user != null) ? (String) user.getRole() : null;
        } catch (Exception e) {
            System.out.println("Error fetching user role: " + e.getMessage());
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public boolean checkUsername(String username) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<User> query = em
                    .createQuery("SELECT u FROM User u JOIN FETCH u.role WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .setMaxResults(1);
            User user = query.getSingleResult();
            em.getTransaction().commit();
            return user != null;
        } catch (NoResultException e) {
            // No user found with the specified username
            return false;
        } catch (Exception e) {
            System.out.println("Error checking username: " + e.getMessage());
            return false;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

}
