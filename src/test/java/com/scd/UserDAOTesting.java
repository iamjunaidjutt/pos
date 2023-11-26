package com.scd;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;

import com.scd.Data.UserDAO;
import com.scd.Models.Role;
import com.scd.Models.User;

public class UserDAOTesting {
    private static UserDAO userDAO;

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
        assertTrue(userDAO.delete(1));
    }

    @Test
    public void userAuthenticateTest() {
        assertTrue(userDAO.userAuthenticate("testUser", "testPassword"));
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

