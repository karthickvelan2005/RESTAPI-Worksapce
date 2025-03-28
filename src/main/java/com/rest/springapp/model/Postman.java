package com.rest.springapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Postman")
public class Postman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "delivery_area")
    private String deliveryArea;

    @Column(name = "assigned_orders")
    private String assignedOrders;

    public Postman() {
    }

    public Postman(int id, String name, String deliveryArea, String assignedOrders) {
        this.id = id;
        this.name = name;
        this.deliveryArea = deliveryArea;
        this.assignedOrders = assignedOrders;
    }

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

    public String getDeliveryArea() {
        return deliveryArea;
    }

    public void setDeliveryArea(String deliveryArea) {
        this.deliveryArea = deliveryArea;
    }

    public String getAssignedOrders() {
        return assignedOrders;
    }

    public void setAssignedOrders(String assignedOrders) {
        this.assignedOrders = assignedOrders;
    }
}
