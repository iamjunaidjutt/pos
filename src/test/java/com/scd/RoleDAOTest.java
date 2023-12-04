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
        role.setRole("MANAGER");

        assertTrue(roleDAO.save(role));
        addedRoleIds.add(role.getId());

        roleDAO.delete(role.getId());
    }

    @Test
    public void testGetAll() {
        List<Object> roles = roleDAO.getAll();
        assertNotNull(roles);
    }

    @Test
    public void testUpdate() {
        Role role = new Role();
        role.setRole("MANAGER");
        roleDAO.save(role);

        role.setRole("SALES_ASSISSTANT");
        assertTrue(roleDAO.update(role));

        roleDAO.delete(role.getId());
    }

    @Test
    public void testDelete() {
        Role role = new Role();
        role.setRole("MANAGER");
        roleDAO.save(role);

        assertTrue(roleDAO.delete(role.getId()));
        addedRoleIds.remove(Integer.valueOf(role.getId()));

        roleDAO.delete(role.getId());
    }
}

