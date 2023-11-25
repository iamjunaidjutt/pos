package com.scd;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.scd.Models.Role;
import com.scd.Models.User;

public class Usertest {

    private User user;
    private Role role;

    @Before
    public void setUp() {
        role = new Role();
        role.setId(1);
        role.setRole("RealRole");

        user = new User();
        user.setId(1);
        user.setUsername("tu");
        user.setPassword("tp");
        user.setRole(role);
    }

    @Test
    public void testGetUsername() {
        assertEquals("tu", user.getUsername());
    }

    @Test
    public void testGetPassword() {
        assertEquals("tp", user.getPassword());
    }

    @Test
    public void testSetRole() {
        assertNotNull(user.getRole());
        assertEquals("RealRole", user.getRole());
    }

    @Test
    public void testGetRole() {
        assertNotNull(user.getRole());
        assertEquals("Unexpected role value", role.getRole(), user.getRole());
    }

    @Test
    public void testGetId() {
        assertEquals(1, user.getId());
    }
}
