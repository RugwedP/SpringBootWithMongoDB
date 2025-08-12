package com.example.mongodb.repo;


import com.example.mongodb.model.Laptop;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LaptopRepo extends MongoRepository<Laptop, ObjectId> {
}
