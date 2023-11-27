package com.scd;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.scd.Data.RoleDAO;
import com.scd.Models.Role;


public class RoleDAOTest {

    private static RoleDAO roleDAO;
    private static List<Integer> addedRoleIds;

    @Before
    public void setUp() {
        roleDAO = new RoleDAO();
        addedRoleIds = new ArrayList<>();
    }

    @After
    public void tearDown() {
        // For each,will clean
        for (int roleId : addedRoleIds) {
            roleDAO.delete(roleId);
        }
        addedRoleIds.clear();
    }

    @Test
    public void testSave() {
        Role role = new Role();
        role.setRole("testRole");

        assertTrue(roleDAO.save(role));
        addedRoleIds.add(role.getId());
    }

    @Test
    public void testGetAll() {
        List<Object> roles = roleDAO.getAll();
        assertNotNull(roles);
    }

    @Test
    public void testUpdate() {
        Role role = new Role();
        role.setRole("testRole");
        roleDAO.save(role);

        role.setRole("newTestRole");
        assertTrue(roleDAO.update(role));
    }

    @Test
    public void testDelete() {
        Role role = new Role();
        role.setRole("testRole");
        roleDAO.save(role);

        assertTrue(roleDAO.delete(role.getId()));
        addedRoleIds.remove(Integer.valueOf(role.getId()));
    }
}

