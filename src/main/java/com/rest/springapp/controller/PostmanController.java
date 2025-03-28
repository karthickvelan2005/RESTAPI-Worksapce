package com.rest.springapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rest.springapp.model.Postman;
import com.rest.springapp.service.PostmanService;

@RestController
public class PostmanController {

    @Autowired
    PostmanService postmanService;

    @PostMapping("/postman/post")
    public ResponseEntity<Postman> addPostman(@RequestBody Postman postman) {
        return new ResponseEntity<>(postmanService.addPostman(postman), HttpStatus.ACCEPTED);
    }

    @GetMapping("/postmen")
    public ResponseEntity<List<Postman>> getAllPostmen() {
        return new ResponseEntity<>(postmanService.getAllPostmen(), HttpStatus.OK);
    }

    @GetMapping("/postman/{id}")
    public ResponseEntity<Postman> getPostmanById(@PathVariable int id) {
        return postmanService.getPostmanById(id)
                .map(postman -> new ResponseEntity<>(postman, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/postman/update/{id}")
    public ResponseEntity<Postman> updatePostman(@PathVariable int id, @RequestBody Postman updatedPostman) {
        try {
            return new ResponseEntity<>(postmanService.updatePostman(id, updatedPostman), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/postman/delete/{id}")
    public ResponseEntity<Void> deletePostman(@PathVariable int id) {
        try {
            postmanService.deletePostman(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
