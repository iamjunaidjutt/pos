package com.scd.Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id", updatable = false, nullable = false)
    private int id;

    private Customer customer;

    @Column(name = "order_date")
    private LocalDateTime date;

    @Column(name = "order_status")
    private String status;

    @Column(name = "order_total")
    private double total;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    /**
     * @return Customer
     */
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        items.add(item);
        item.setOrder(this);
        item.setCart(null);
    }

    public void removeItem(Item item) {
        items.remove(item);
        item.setOrder(null);
        item.setCart(null);
    }

    @Override
    public String toString() {
        return "Orders [id=" + id + ", customer=" + customer + ", date=" + date + ", status=" + status + ", total="
                + total + ", items=" + items + "]";
    }

}
