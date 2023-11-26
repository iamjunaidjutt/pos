package com.scd.Models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order extends Cart {
    private Customer customer;

    @Column(name = "o_date")
    private Date date;

    @Column(name = "o_status")
    private String status;

    @Column(name = "o_total")
    private double total;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    @Override
    public String toString() {
        return "Order [customer=" + customer + ", date=" + date + ", status=" + status + ", total=" + total + "]";
    }

}
