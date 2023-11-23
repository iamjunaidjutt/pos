package com.scd.Data;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.scd.Models.Role;

public class RoleDAO implements DAO {
    private EntityManagerFactory emf;

    public RoleDAO() {
        emf = Persistence.createEntityManagerFactory("pu");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public boolean create(Object obj) {
        Role role = (Role) obj;
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(role);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error creating role: " + e.getMessage());
            return false;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<Object> read() {
        List<Object> roles = null;
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            roles = em.createQuery("SELECT r FROM Role r", Object.class).getResultList();
            em.getTransaction().commit();
            return roles;
        } catch (Exception e) {
            System.out.println("Error reading roles: " + e.getMessage());
            return roles;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public boolean update(Object obj) {
        Role role = (Role) obj;
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(role);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error updating role: " + e.getMessage());
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
            Role role = em.find(Role.class, id);
            em.remove(role);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error deleting role: " + e.getMessage());
            return false;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
