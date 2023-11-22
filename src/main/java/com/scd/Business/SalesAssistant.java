package com.scd.Business;

import com.scd.Models.Role;

public class SalesAssistant extends Role {
    public SalesAssistant() {
        this.role = "SALES_ASSISTANT";
    }

    @Override
    public boolean permission(String permission) {
        return permission.equals("process_order_transaction") || permission.equals("view_inventory")
                || permission.equals("view_sales_report");
    }

    public void processOrder() {
        System.out.println("Processing order...");
    }
}
