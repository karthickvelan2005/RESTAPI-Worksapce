package com.rest.springapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rest.springapp.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    // Fetch all Products
    @Query("SELECT p FROM Product p")
    List<Product> getAllProducts();

    // Fetch a Product by ID
    @Query("SELECT p FROM Product p WHERE p.id = ?1")
    Optional<Product> getProductById(Long id);

    // Fetch Products by Name
    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
    List<Product> findByName(String name);

    // Fetch Products by Price Range
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN ?1 AND ?2")
    List<Product> findByPriceRange(double minPrice, double maxPrice);

    // Fetch Products by Order ID
    @Query("SELECT p FROM Product p WHERE p.order.id = ?1")
    List<Product> findByOrderId(int orderId);
}
