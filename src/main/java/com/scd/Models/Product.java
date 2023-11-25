package com.scd.Models;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "p_code", updatable = false, nullable = false)
    private int code;

    @Column(name = "p_name", unique = true)
    private String name;

    @Column(name = "p_description")
    private String description;

    @Column(name = "p_price")
    private double price;

    @Column(name = "p_stock_quantity")
    private int stockQuantity;

    @Column(name = "p_expiration_date")
    private Date expirationDate;

    @ManyToMany(cascade = javax.persistence.CascadeType.ALL)
    private Collection<Category> categories = new ArrayList<>();

    public int getCode() {
        return code;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date string) {
        this.expirationDate = string;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Collection<Category> getCategories() {
        return categories;
    }

    public void setCategories(Collection<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Product [code=" + code + ", name=" + name + ", description=" + description + ", price=" + price
                + ", stockQuantity=" + stockQuantity + ", expirationDate=" + expirationDate + "]";
    }

}