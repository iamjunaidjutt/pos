package com.scd.Models;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Customer {
    @Column(name = "c_name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer [name=" + name + "]";
    }

}
