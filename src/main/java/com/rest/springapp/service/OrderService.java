package com.rest.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rest.springapp.model.Order;
import com.rest.springapp.repository.OrderRepo;

@Service
public class OrderService {
    @Autowired
    OrderRepo orderRepo;

    public Order addOrder(Order order) {
        return orderRepo.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public Optional<Order> getOrderById(Integer id) {
        return orderRepo.findById(id);
    }

    public Order updateOrder(Integer id, Order newOrder) {
        return orderRepo.findById(id).map(existingOrder -> {
            existingOrder.setStatus(newOrder.getStatus());
            existingOrder.setUser(newOrder.getUser());
            existingOrder.setGardener(newOrder.getGardener());
            existingOrder.setProducts(newOrder.getProducts());
            return orderRepo.save(existingOrder);
        }).orElseThrow(() -> new RuntimeException("Order not found with id " + id));
    }

    public void deleteOrder(Integer id) {
        if (orderRepo.existsById(id)) {
            orderRepo.deleteById(id);
        } else {
            throw new RuntimeException("Order not found with id " + id);
        }
    }
    // Sorting
    public List<Order> getAllOrdersSorted(String field) {
        return orderRepo.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    // Pagination
    public List<Order> getPaginatedOrders(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return orderRepo.findAll(pageable).getContent();
    }

    // Pagination & Sorting
    public List<Order> getPaginatedAndSortedOrders(int pageNumber, int pageSize, String field) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, field));
        return orderRepo.findAll(pageable).getContent();
    }
}
