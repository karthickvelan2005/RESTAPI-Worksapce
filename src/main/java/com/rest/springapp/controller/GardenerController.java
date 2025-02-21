package com.rest.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rest.springapp.model.Gardener;
import com.rest.springapp.service.GardenerService;

@RestController
public class GardenerController {

    @Autowired
    GardenerService gardenerService;

    @PostMapping("/gardener/post")
    public ResponseEntity<Gardener> addGardener(@RequestBody Gardener gardener) {
        return new ResponseEntity<>(gardenerService.addGardener(gardener), HttpStatus.ACCEPTED);
    }

    @GetMapping("/gardeners")
    public ResponseEntity<List<Gardener>> getAllGardeners() {
        return new ResponseEntity<>(gardenerService.getAllGardeners(), HttpStatus.OK);
    }

    @GetMapping("/gardener/{id}")
    public ResponseEntity<Gardener> getGardenerById(@PathVariable int id) {
        return gardenerService.getGardenerById(id)
                .map(gardener -> new ResponseEntity<>(gardener, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/gardener/update/{id}")
    public ResponseEntity<Gardener> updateGardener(@PathVariable int id, @RequestBody Gardener updatedGardener) {
        try {
            return new ResponseEntity<>(gardenerService.updateGardener(id, updatedGardener), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/gardener/delete/{id}")
    public ResponseEntity<Void> deleteGardener(@PathVariable int id) {
        try {
            gardenerService.deleteGardener(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
     // Sorting
     @GetMapping("/sort/{field}")
     public ResponseEntity<List<Gardener>> getSortedGardeners(@PathVariable String field) {
         return new ResponseEntity<>(gardenerService.getAllGardenersSorted(field), HttpStatus.OK);
     }
 
     // Pagination
     @GetMapping("/paginate/{pageSize}/{pageNumber}")
     public ResponseEntity<List<Gardener>> getPaginatedGardeners(@PathVariable int pageSize, @PathVariable int pageNumber) {
         return new ResponseEntity<>(gardenerService.getPaginatedGardeners(pageNumber, pageSize), HttpStatus.OK);
     }
 
     // Pagination & Sorting
     @GetMapping("/paginate/{pageSize}/{pageNumber}/{field}")
     public ResponseEntity<List<Gardener>> getPaginatedAndSortedGardeners(
             @PathVariable int pageSize,
             @PathVariable int pageNumber,
             @PathVariable String field) {
         return new ResponseEntity<>(gardenerService.getPaginatedAndSortedGardeners(pageNumber, pageSize, field), HttpStatus.OK);
     }
}
