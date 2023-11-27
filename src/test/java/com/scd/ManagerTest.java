package com.scd;
import static org.junit.Assert.*;

import org.junit.Test;

import com.scd.Business.Manager;

public class ManagerTest {

    @Test
    public void testPermission() {
        Manager manager = new Manager();

        assertTrue(manager.permission("process_order_transaction"));
        assertTrue(manager.permission("view_inventory"));
        assertTrue(manager.permission("view_sales_report"));
        assertTrue(manager.permission("add_inventory"));
        assertTrue(manager.permission("add_product"));
        assertTrue(manager.permission("add_user"));
        assertTrue(manager.permission("add_role"));
        assertTrue(manager.permission("remove_inventory"));
        assertTrue(manager.permission("remove_product"));
        assertTrue(manager.permission("remove_user"));
        assertTrue(manager.permission("remove_role"));

        assertFalse(manager.permission("invalid_permission"));
    }


}

