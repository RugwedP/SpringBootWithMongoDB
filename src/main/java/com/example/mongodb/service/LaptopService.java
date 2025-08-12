package com.example.mongodb.service;

import com.example.mongodb.model.Employee;
import com.example.mongodb.model.Laptop;
import com.example.mongodb.repo.EmpRepo;
import com.example.mongodb.repo.LaptopRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaptopService {


    @Autowired
    private LaptopRepo laptopRepo;

    @Autowired
    private EmpRepo empRepo;


    public ResponseEntity<?> addLaptop(Laptop laptop)
    {
        return new ResponseEntity<>(this.laptopRepo.save(laptop), HttpStatus.OK);

    }

    public ResponseEntity<?> findById(ObjectId id) {

        Optional<Laptop> optionalLaptop = this.laptopRepo.findById(id);

        if(optionalLaptop.isPresent())
        {

            return new ResponseEntity<>(optionalLaptop.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>("Laptop Not found",HttpStatus.NOT_FOUND);


    }


    public ResponseEntity<?> findAllLAptops() {

        List<Laptop> laptops = this.laptopRepo.findAll();
        if(laptops.isEmpty())
        {
            return  new ResponseEntity<>("Laptops are not found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(laptops,HttpStatus.OK);

    }

    public ResponseEntity<?> makeRekation(ObjectId userId, ObjectId laptopId) {


        Optional<Employee> optionalEmployee = this.empRepo.findById(userId);
        Optional<Laptop> optionalLaptop = this.laptopRepo.findById(laptopId);

        if(optionalEmployee.isPresent() && optionalLaptop.isPresent())
        {
            Laptop laptop = new Laptop();
            Employee employee = new Employee();
            
            laptop = optionalLaptop.get();
            employee = optionalEmployee.get();


            employee.setLaptops(laptop);

            return new ResponseEntity<>(this.empRepo.save(employee),HttpStatus.CREATED);

        }
        else
        {
            return new ResponseEntity<>("Employee or Laptop is not present",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> deleteById(ObjectId id) {

        List<Employee> empWithSameLaptop = this.empRepo.findByLaptops_Id(id);

        for( Employee emp: empWithSameLaptop)
        {
            emp.getLaptops().removeIf(l->l.getId().equals(id));
            empRepo.save(emp);
        }
     return new ResponseEntity<>(HttpStatus.OK);
    }
}
