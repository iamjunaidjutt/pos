package com.scd;
import static org.junit.Assert.*;
import org.junit.Test;

import com.scd.Business.SalesAssistant;

public class SalesAssistantTest {

    @Test
    public void testPermission() {
        SalesAssistant salesAssistant = new SalesAssistant();

        assertTrue(salesAssistant.permission("process_order_transaction"));
        assertTrue(salesAssistant.permission("view_inventory"));
        assertTrue(salesAssistant.permission("view_sales_report"));

        assertFalse(salesAssistant.permission("invalid_permission"));
    }

    @Test
    public void testProcessOrder() {
        SalesAssistant salesAssistant = new SalesAssistant();
        salesAssistant.processOrder();
    }


}

