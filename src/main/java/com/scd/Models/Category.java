package com.scd.Models;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "c_code", updatable = false, nullable = false)
    private int code;

    @Column(name = "c_name", unique = true)
    private String name;

    @Column(name = "c_description")
    private String description;

    // @ManyToMany
    // @JoinTable(name = "subcategory_relationship", joinColumns = @JoinColumn(name
    // = "parent_category_id"), inverseJoinColumns = @JoinColumn(name =
    // "subcategory_id"))
    // private List<Category> subcategories = new ArrayList<>();

    @ManyToMany(mappedBy = "categories", fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // public List<Category> getSubcategories() {
    // return subcategories;
    // }

    // public void setSubcategories(List<Category> subcategories) {
    // this.subcategories = subcategories;
    // }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category [code=" + code + ", name=" + name + ", description=" + description + ", products=" + products
                + "]";
    }

}
