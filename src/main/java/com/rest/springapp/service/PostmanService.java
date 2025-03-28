package com.rest.springapp.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rest.springapp.model.Postman;
import com.rest.springapp.repository.PostmanRepo;

@Service
public class PostmanService {

    @Autowired
    private PostmanRepo postmanRepo;

    public Postman addPostman(Postman postman) {
        return postmanRepo.save(postman);
    }

    public List<Postman> getAllPostmen() {
        return postmanRepo.findAll();
    }

    public Optional<Postman> getPostmanById(Integer id) {
        return postmanRepo.findById(id);
    }

    public Postman updatePostman(Integer id, Postman newPostman) {
        return postmanRepo.findById(id).map(existingPostman -> {
            existingPostman.setName(newPostman.getName());
            existingPostman.setDeliveryArea(newPostman.getDeliveryArea());
            existingPostman.setAssignedOrders(newPostman.getAssignedOrders());
            return postmanRepo.save(existingPostman);
        }).orElseThrow(() -> new RuntimeException("Postman not found with id " + id));
    }

    public void deletePostman(Integer id) {
        if (postmanRepo.existsById(id)) {
            postmanRepo.deleteById(id);
        } else {
            throw new RuntimeException("Postman not found with id " + id);
        }
    }
}
