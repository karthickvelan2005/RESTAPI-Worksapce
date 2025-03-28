package com.rest.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rest.springapp.model.Postman;

@Repository
public interface PostmanRepo extends JpaRepository<Postman, Integer> {
}
