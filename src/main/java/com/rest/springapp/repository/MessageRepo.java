package com.rest.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.springapp.model.Message;

@Repository
public interface MessageRepo extends JpaRepository<Message,Integer>{

}
