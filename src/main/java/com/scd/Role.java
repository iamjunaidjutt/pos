package com.scd;

import javax.persistence.*;

@MappedSuperclass
public abstract class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Column(name = "username")
    protected String username;

    @Column(name = "password")
    protected String password;

    @Column(name = "role")
    protected String role;

    public abstract void save();

    public abstract boolean login(String username, String password);
}

