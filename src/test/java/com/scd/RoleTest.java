package com.scd;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.scd.Models.Role;

public class RoleTest {
    private Role r1;

    @Before
    public void setUp() {
        r1 = new Role();
        
        r1.setId(1);
        r1.setRole("role_user");
    }

    @Test
    public void testGetRole() {
        assertEquals("role_user", r1.getRole());
    }

    @Test
    public void testSetRole() {
        r1.setRole("role_admin");
        assertEquals("role_admin", r1.getRole());
    }

    @Test
    public void testPermission() {
        assertEquals(false, r1.permission("p1"));
    }

    @Test
    public void testGetId() {
        assertEquals(1, r1.getId());
    }

    @Test
    public void testSetId() {
        r1.setId(2);
        assertEquals(2, r1.getId());
    }
}

