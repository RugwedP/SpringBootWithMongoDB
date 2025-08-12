package com.example.mongodb.repo;



import com.example.mongodb.model.Employee;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpRepo extends MongoRepository<Employee, ObjectId> {

    List<Employee> findByLaptops_Id(ObjectId id);

}
