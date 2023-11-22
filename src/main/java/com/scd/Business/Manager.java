package com.scd.Business;

import com.scd.Models.Role;

public class Manager extends Role {
    public Manager() {
        this.role = "manager";
    }

    @Override
    public boolean permission(String permission) {
        return permission.equals("process_order_transaction") || permission.equals("view_inventory")
                || permission.equals("view_sales_report") || permission.equals("add_inventory")
                || permission.equals("add_product") || permission.equals("add_user") || permission.equals("add_role")
                || permission.equals("remove_inventory") || permission.equals("remove_product")
                || permission.equals("remove_user") || permission.equals("remove_role");
    }
}
