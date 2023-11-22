package com.scd.Data;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.scd.Models.User;

public class UserDAO implements DAO {
    EntityManagerFactory emf;
    EntityManager em;

    public UserDAO() {
        emf = Persistence.createEntityManagerFactory("pu");
        em = emf.createEntityManager();
    }

    @Override
    public boolean create(Object obj) {
        User user = (User) obj;
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Object> read() {
        List<Object> users = null;
        try {
            em.getTransaction().begin();
            users = em.createQuery("SELECT u FROM User u", Object.class).getResultList();
            em.getTransaction().commit();
            return users;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return users;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean update(Object obj) {
        User user = (User) obj;
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean delete(UUID id) {
        try {
            em.getTransaction().begin();
            em.remove(em.find(User.class, id));
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    public boolean userAuthenticate(String username, String password) {
        try {
            em.getTransaction().begin();
            User user = em.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password",
                    User.class).setParameter("username", username).setParameter("password", password)
                    .getSingleResult();
            em.getTransaction().commit();
            if (user != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    public String userRole(String username, String password) {
        try {
            em.getTransaction().begin();
            User user = em.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password",
                    User.class).setParameter("username", username).setParameter("password", password)
                    .getSingleResult();
            em.getTransaction().commit();
            if (user != null) {
                return user.getRole().getRole();
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }
}
