package com.scd.Models;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "carts")
@Inheritance(strategy = InheritanceType.JOINED)
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected String id;

    @OneToMany(mappedBy = "cart")
    protected Collection<Item> items;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Collection<Item> getItems() {
        return items;
    }

    public void setItems(Collection<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart [id=" + id + ", items=" + items + "]";
    }

}
