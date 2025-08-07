package com.example.mongodb.repo;



import com.example.mongodb.model.Employee;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpRepo extends MongoRepository<Employee, ObjectId> {


}
