package com.rest.springapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products") // Avoids conflicts with SQL reserved keywords
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private int id;

    @Column(nullable = false) // Ensures name cannot be null
    private String name;

    @Column(nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "order_id") // Foreign key reference to Order
    private Order order;

    // Default Constructor
    public Product() {
    }

    // Parameterized Constructor
    public Product(int id, String name, double price, Order order) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.order = order;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
