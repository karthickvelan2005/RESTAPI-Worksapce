package com.rest.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rest.springapp.model.Order;
import com.rest.springapp.service.OrderService;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/order/post")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        return new ResponseEntity<>(orderService.addOrder(order), HttpStatus.ACCEPTED);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable int id) {
        return orderService.getOrderById(id)
                .map(order -> new ResponseEntity<>(order, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/order/update/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable int id, @RequestBody Order updatedOrder) {
        try {
            return new ResponseEntity<>(orderService.updateOrder(id, updatedOrder), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/order/delete/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int id) {
        try {
            orderService.deleteOrder(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Sorting
    @GetMapping("/order/sort/{field}")
    public ResponseEntity<List<Order>> getSortedOrders(@PathVariable String field) {
        return new ResponseEntity<>(orderService.getAllOrdersSorted(field), HttpStatus.OK);
    }

    // Pagination
    @GetMapping("/order/paginate/{pageSize}/{pageNumber}")
    public ResponseEntity<List<Order>> getPaginatedOrders(@PathVariable int pageSize, @PathVariable int pageNumber) {
        return new ResponseEntity<>(orderService.getPaginatedOrders(pageNumber, pageSize), HttpStatus.OK);
    }

    // Pagination & Sorting
    @GetMapping("/order/paginate/{pageSize}/{pageNumber}/{field}")
    public ResponseEntity<List<Order>> getPaginatedAndSortedOrders(
            @PathVariable int pageSize,
            @PathVariable int pageNumber,
            @PathVariable String field) {
        return new ResponseEntity<>(orderService.getPaginatedAndSortedOrders(pageNumber, pageSize, field), HttpStatus.OK);
    }
}
