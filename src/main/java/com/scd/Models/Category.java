package com.scd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Category {
    private String code;
    private String name;
    private String description;
    private List<Category> subcategories;
    private List<Item> items;

    public Category(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.subcategories = new ArrayList<>();
        this.items = new ArrayList<>();
    }

    public String getCategoryCode() {
        return code;
    }

    public void setCategoryCode(String code) {
        this.code = code;
    }

    public String getCategoryName() {
        return name;
    }

    public void setCategoryName(String name) {
        this.name = name;
    }

    public String getCategoryDescription() {
        return description;
    }

    public void setCategoryDescription(String description) {
        this.description = description;
    }

    public List<Category> getSubcategories() {
        return new ArrayList<>(subcategories);
    }

    public void add(Category subcategory) {
        subcategories.add(subcategory);
    }

    public void remove(Category subcategory) {
        subcategories.remove(subcategory);
    }

    public List<Item> getItems() {
        return new ArrayList<>(items);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(code, category.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
