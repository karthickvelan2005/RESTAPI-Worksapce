package com.rest.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rest.springapp.model.Product;
import com.rest.springapp.repository.ProductRepo;
@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepo.findById(id);
    }

    public Product updateProduct(Long id, Product newProduct) {
        return productRepo.findById(id).map(existingProduct -> {
            existingProduct.setName(newProduct.getName());
            existingProduct.setPrice(newProduct.getPrice());
            existingProduct.setOrder(newProduct.getOrder());
            return productRepo.save(existingProduct);
        }).orElseThrow(() -> new RuntimeException("Product not found with id " + id));
    }

    public void deleteProduct(Long id) {
        if (productRepo.existsById(id)) {
            productRepo.deleteById(id);
        } else {
            throw new RuntimeException("Product not found with id " + id);
        }
    }
     // Sorting
    public List<Product> sortProducts(String field) {
        return productRepo.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    // Pagination
    public List<Product> paginateProducts(int pageSize, int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return productRepo.findAll(page).getContent();
    }

    // Pagination & Sorting
    public List<Product> paginateAndSortProducts(int pageSize, int pageNumber, String field) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, field));
        return productRepo.findAll(pageable).getContent();
    }
}
