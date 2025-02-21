package com.rest.springapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rest.springapp.model.Product;
import com.rest.springapp.service.ProductService;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product/post")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.CREATED);
    }

    @GetMapping("/product/getall")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/product/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        try {
            return new ResponseEntity<>(productService.updateProduct(id, updatedProduct), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/product/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Sorting Products
    @GetMapping("/product/sort/{field}")
    public ResponseEntity<List<Product>> sortProducts(@PathVariable String field) {
        return new ResponseEntity<>(productService.sortProducts(field), HttpStatus.OK);
    }

    // Pagination
    @GetMapping("/product/paginate/{pageSize}/{pageNumber}")
    public ResponseEntity<List<Product>> paginateProducts(
            @PathVariable int pageSize,
            @PathVariable int pageNumber) {
        return new ResponseEntity<>(productService.paginateProducts(pageNumber, pageSize), HttpStatus.OK);
    }

    // Pagination & Sorting
    @GetMapping("/product/paginate/{pageSize}/{pageNumber}/{field}")
    public ResponseEntity<List<Product>> paginateAndSortProducts(
            @PathVariable int pageSize,
            @PathVariable int pageNumber,
            @PathVariable String field) {
        return new ResponseEntity<>(productService.paginateAndSortProducts(pageNumber, pageSize, field), HttpStatus.OK);
    }
}
