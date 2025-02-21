package com.rest.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rest.springapp.model.User;
import com.rest.springapp.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/post")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/user/getall")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        return userService.getUserById(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/user/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        try {
            return new ResponseEntity<>(userService.updateUser(id, updatedUser), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/sort/{field}")
    public ResponseEntity<List<User>> sortUsers(@PathVariable String field) {
        return new ResponseEntity<>(userService.sortUsers(field), HttpStatus.OK);
    }

    @GetMapping("/user/paginate/{pageSize}/{pageNumber}")
    public ResponseEntity<List<User>> paginateUsers(@PathVariable int pageSize, @PathVariable int pageNumber) {
        return new ResponseEntity<>(userService.paginateUsers(pageSize, pageNumber), HttpStatus.OK);
    }

    @GetMapping("/user/paginate/{pageSize}/{pageNumber}/{field}")
    public ResponseEntity<List<User>> paginateAndSortUsers(@PathVariable int pageSize, @PathVariable int pageNumber, @PathVariable String field) {
        return new ResponseEntity<>(userService.paginateAndSortUsers(pageSize, pageNumber, field), HttpStatus.OK);
    }
}
