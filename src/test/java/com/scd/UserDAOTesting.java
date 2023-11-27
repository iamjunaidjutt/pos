package com.scd;

import static org.junit.Assert.*;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.scd.Data.UserDAO;
import com.scd.Models.Role;
import com.scd.Models.User;

public class UserDAOTesting {

    private static UserDAO userDAO;
    private static EntityManagerFactory entityManagerFactory;

    @BeforeClass
    public static void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        userDAO = new UserDAO();
    }

    @AfterClass
    public static void tearDown() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }

    @Test
    public void saveUserTest() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");

        Role role = new Role();
        role.setRole("ROLE_USER");
        user.setRole(role);
        assertTrue(userDAO.save(user));
    }

    @Test
    public void getAllUsersTest() {
        List<Object> users = userDAO.getAll();
        assertNotNull(users);
        assertTrue(users.size() > 0);
    }

    @Test
    public void deleteUserTest() {
        // Create a user and save it
        User userToDelete = new User();
        userToDelete.setUsername("userToDelete");
        userToDelete.setPassword("passwordToDelete");

        Role role = new Role();
        role.setRole("ROLE_USER");
        userToDelete.setRole(role);

        assertTrue(userDAO.save(userToDelete));

        // Now, delete the user
        assertTrue(userDAO.delete(userToDelete.getId()));
    }

    @Test
    public void userAuthenticateTest() {
        assertTrue(userDAO.userAuthenticate("testUser", "testPassword"));
        assertFalse(userDAO.userAuthenticate("nonexistentUser", "password"));
    }

    @Test
    public void userRoleTest() {
        String role = userDAO.userRole("testUser", "testPassword");
        assertEquals("ROLE_USER", role);
    }

    @Test
    public void checkUsernameTest() {
        assertTrue(userDAO.checkUsername("testUser"));
        assertFalse(userDAO.checkUsername("nonexistentUser"));
    }
}
