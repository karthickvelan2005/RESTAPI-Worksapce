package com.rest.springapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rest.springapp.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    // Fetch all Users
    @Query("SELECT u FROM User u")
    List<User> getAllUsers();

    // Fetch a User by ID
    @Query("SELECT u FROM User u WHERE u.id = ?1")
    Optional<User> getUserById(int id);

    // Fetch a User by Email
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> getUserByEmail(String email);

    // Fetch Users by Name (partial match)
    @Query("SELECT u FROM User u WHERE u.name LIKE %?1%")
    List<User> findByName(String name);

    // Fetch all Orders for a User
    @Query("SELECT u.orders FROM User u WHERE u.id = ?1")
    List<Object> getUserOrders(int userId);
}
