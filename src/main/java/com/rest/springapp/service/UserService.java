package com.rest.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rest.springapp.model.User;
import com.rest.springapp.repository.UserRepo;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User addUser(User user) {
        return userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public Optional<User> getUserById(int id) {
        return userRepo.findById(id);
    }

    public User updateUser(int id, User newUser) {
        return userRepo.findById(id).map(existingUser -> {
            existingUser.setName(newUser.getName());
            existingUser.setEmail(newUser.getEmail());
            existingUser.setOrders(newUser.getOrders());
            return userRepo.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    public void deleteUser(int id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
        } else {
            throw new RuntimeException("User not found with id " + id);
        }
    }

    public List<User> sortUsers(String field) {
        return userRepo.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    public List<User> paginateUsers(int pageSize, int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return userRepo.findAll(page).getContent();
    }

    public List<User> paginateAndSortUsers(int pageSize, int pageNumber, String field) {
        return userRepo.findAll(PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC, field))).getContent();
    }
}
