package com.rest.springapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "customer_order")  // Renamed table to avoid SQL conflicts
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "user")
    private String user;

    @Column(name = "gardener")
    private String gardener;

    @Column(name = "products")
    private String products;

    // Default Constructor (Required by JPA)
    public Order() {
    }

    // Parameterized Constructor
    public Order(int id, String status, String user, String gardener, String products) {
        this.id = id;
        this.status = status;
        this.user = user;
        this.gardener = gardener;
        this.products = products;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getGardener() {
        return gardener;
    }

    public void setGardener(String gardener) {
        this.gardener = gardener;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }
}
