package com.rest.springapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Gardener")
public class Gardener {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "order_details")  
    private String orderDetails;
    public Gardener() {
    }
    public Gardener(int id, String name, String specialization, String orderDetails) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.orderDetails = orderDetails;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }
}
