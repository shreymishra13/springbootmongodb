package com.shrey.springbootmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.shrey.springbootmongodb.model.todoDTO;


@Repository
public interface ToDoRepository extends MongoRepository<todoDTO, String> {

}
