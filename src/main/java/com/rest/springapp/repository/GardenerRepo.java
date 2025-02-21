package com.rest.springapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rest.springapp.model.Gardener;

@Repository
public interface GardenerRepo extends JpaRepository<Gardener, Integer> {
    @Query("SELECT g FROM Gardener g")
    List<Gardener> getAllGardeners();
    @Query("SELECT g FROM Gardener g WHERE g.id = ?1")
    Optional<Gardener> getGardenerById(int id);
    @Query("SELECT g FROM Gardener g WHERE g.specialization = ?1")
    List<Gardener> findBySpecialization(String specialization);
    @Query("SELECT g FROM Gardener g WHERE LOWER(g.name) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Gardener> findByNameContaining(String name);
    @Query("SELECT g FROM Gardener g WHERE g.orderDetails = ?1")
    List<Gardener> findByOrderDetails(String orderDetails);
}


