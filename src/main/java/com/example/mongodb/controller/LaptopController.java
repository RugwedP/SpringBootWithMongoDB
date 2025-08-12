package com.example.mongodb.controller;

import com.example.mongodb.model.Laptop;
import com.example.mongodb.service.LaptopService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("laptop")
public class LaptopController {


    @Autowired
    private LaptopService laptopService;

    @PostMapping
    public ResponseEntity<?> addLaptop(@RequestBody Laptop laptop)
    {
       return this.laptopService.addLaptop(laptop);
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<?> getLapTopById(@PathVariable("id")ObjectId id)
    {
       return this.laptopService.findById(id);
    }

    @GetMapping("getLaptop")
    public ResponseEntity<?> getAllLaptops()
    {
        return this.laptopService.findAllLAptops();
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteLaptop(@PathVariable("id") ObjectId id)
    {

        return this.laptopService.deleteById(id);
    }








}
