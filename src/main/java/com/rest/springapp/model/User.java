package com.rest.springapp.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")  // Avoids conflicts with SQL reserved keywords
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generate ID
    private int id;

    @Column(nullable = false)  // Ensures name cannot be null
    private String name;

    @Column(unique = true, nullable = false)  // Ensures unique and non-null email
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)  // One user has many orders
    private List<Order> orders;

    // Default constructor (required by JPA)
    public User() {
    }

    // Parameterized constructor
    public User(int id, String name, String email, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.orders = orders;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
