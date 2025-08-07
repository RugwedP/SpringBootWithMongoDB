package com.example.mongodb.controller;

import com.example.mongodb.model.Employee;
import com.example.mongodb.service.EmpService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("emp")
public class EmpController {


    @Autowired
    private EmpService empService;


    // add emp
    @PostMapping("add")
    public ResponseEntity<?> addEmp(@RequestBody Employee employee)
    {
       return this.empService.addEmp(employee);
    }

    @GetMapping("findById/{id}")
    public Employee findEmp(@PathVariable("id") ObjectId id)
    {
       return this.empService.findEmpById(id);
    }

    @GetMapping("findAll")
    public List<Employee> findEmps()
    {
        return this.empService.findAllEmp();
    }


    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") ObjectId id)
    {
        return this.empService.deleteById(id);
    }

    @PutMapping("updateEmp/{id}")
    public ResponseEntity<?> updateEmp(@PathVariable("id") ObjectId id,@RequestBody Employee employee)
    {
        return this.empService.updateById(id,employee);
    }



}
