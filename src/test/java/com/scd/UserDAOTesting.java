package com.scd;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import com.scd.Models.Role;
import com.scd.Data.UserDAO;
import com.scd.Models.User;

public class UserDAOTesting {

    private static UserDAO userDAO;
    private static List<Integer> addedUserIds = new ArrayList<>();

    @BeforeClass
    public static void setUp() {
        userDAO = new UserDAO();
    }

//     @After
// public void tearDown() {
//     // For each clean
//     for (int userId : addedUserIds) {
//         userDAO.delete(userId);
//     }
//     addedUserIds.clear();
// }

// @AfterClass
// public static void tearDownClass() {
//     //total clean
//     for (int userId : addedUserIds) {
//         userDAO.delete(userId);
//     }
//     addedUserIds.clear();
// }



@After
public void tearDown() {
    try {
        // For each clean
        for (int userId : addedUserIds) {
            userDAO.delete(userId);
        }
        addedUserIds.clear();
    } catch (Exception e) {
        e.printStackTrace();
    }
}






    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");

        assertTrue(userDAO.save(user));
        addedUserIds.add(user.getId());
    }

    @Test
    public void testGetAll() {
        List<Object> users = userDAO.getAll();
        assertNotNull(users);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");

        userDAO.save(user);

        user.setPassword("newPassword");
        assertTrue(userDAO.update(user));
    }

    @Test
    public void testDelete() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");

        userDAO.save(user);

        assertTrue(userDAO.delete(user.getId()));
        addedUserIds.remove(Integer.valueOf(user.getId()));
    }

    @Test
    public void testUserAuthenticate() {
        assertTrue(userDAO.userAuthenticate("testUser", "testPassword"));
    }

    @Test
    public void testUserRole() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");

        Role role = new Role();
        role.setRole("MANAGER");
        user.setRole(role);

        assertTrue(userDAO.save(user));
        addedUserIds.add(user.getId());

        assertEquals("MANAGER", userDAO.userRole("testUser", "testPassword"));
    }

    @Test
    public void testCheckUsername() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");

        assertTrue(userDAO.save(user));
        addedUserIds.add(user.getId());

        assertFalse(userDAO.checkUsername("testUser"));
        assertFalse(userDAO.checkUsername("nonexistentUser"));
    }
}