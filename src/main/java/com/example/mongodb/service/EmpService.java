package com.example.mongodb.service;


import com.example.mongodb.model.Employee;
import com.example.mongodb.repo.EmpRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmpService {

    @Autowired
    private EmpRepo empRepo;

    public ResponseEntity<?> addEmp(Employee employee) {

        employee.setDateTime(LocalDateTime.now());
        this.empRepo.save(employee);

        return new ResponseEntity<>("Employee saved successfully",HttpStatus.CREATED);

    }

    public Employee findEmpById(ObjectId id) {


       return empRepo.findById(id).orElse(null);


    }

    public List<Employee> findAllEmp() {

        return this.empRepo.findAll();
    }

    public ResponseEntity<?> deleteById(ObjectId id) {

        Optional<Employee> optionalEmployee = empRepo.findById(id);

        if(optionalEmployee.isPresent())
        {
            Employee employee = new Employee();
            employee = optionalEmployee.get();

            this.empRepo.deleteById(employee.getId());
            return  new ResponseEntity<>(HttpStatus.OK);
        }
        else
        {
            return  new ResponseEntity<>("Employee Not Found",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> updateById(ObjectId id,Employee updatedEmp) {
        Optional<Employee> optionalEmployee = empRepo.findById(id);
        if(optionalEmployee.isPresent())
        {
            Employee existingEmp = optionalEmployee.get();


            if (updatedEmp.getName() != null) existingEmp.setName(updatedEmp.getName());
            if (updatedEmp.getDepartment() != null) existingEmp.setDepartment(updatedEmp.getDepartment());
            if (updatedEmp.getSalary() != null) existingEmp.setSalary(updatedEmp.getSalary());
            if (updatedEmp.getCity() != null) existingEmp.setCity(updatedEmp.getCity());
            if (updatedEmp.getState() != null) existingEmp.setState(updatedEmp.getState());

            empRepo.save(existingEmp);

            return  new ResponseEntity<>(existingEmp,HttpStatus.OK);
        }
        else
        {
            return  new ResponseEntity<>("Employee Not Found",HttpStatus.NOT_FOUND);
        }
    }
}
