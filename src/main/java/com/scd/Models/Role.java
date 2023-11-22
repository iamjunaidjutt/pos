package com.scd.Models;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public abstract class Role {
    @Column(name = "user_role")
    protected String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Role [role=" + role + "]";
    }

    public boolean permission(String permission) {
        return false;
    }
}
