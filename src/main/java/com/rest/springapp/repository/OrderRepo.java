// package com.rest.springapp.repository;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;
// import com.rest.springapp.model.Order;

// @Repository
// public interface OrderRepo extends JpaRepository<Order, Integer> {
// }
package com.rest.springapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rest.springapp.model.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {

    // Fetch all Orders
    @Query("SELECT o FROM Order o")
    List<Order> getAllOrders();

    // Fetch an Order by ID
    @Query("SELECT o FROM Order o WHERE o.id = ?1")
    Optional<Order> getOrderById(int id);

    // Fetch Orders by Status
    @Query("SELECT o FROM Order o WHERE o.status = ?1")
    List<Order> findByStatus(String status);

    // Fetch Orders by User
    @Query("SELECT o FROM Order o WHERE o.user = ?1")
    List<Order> findByUser(String user);

    // Fetch Orders by Gardener
    @Query("SELECT o FROM Order o WHERE o.gardener = ?1")
    List<Order> findByGardener(String gardener);

    // Fetch Orders containing a specific product
    @Query("SELECT o FROM Order o WHERE o.products LIKE %?1%")
    List<Order> findByProduct(String product);
}
