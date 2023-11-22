package com.scd.Models;

import java.util.List;

public abstract class ItemContainer {
    private String id;
    List<Item> items;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ItemContainer [id=" + id + ", items=" + items + "]";
    }

}
