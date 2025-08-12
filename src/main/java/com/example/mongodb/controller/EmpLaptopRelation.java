package com.example.mongodb.controller;


import com.example.mongodb.service.LaptopService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("empLaptop")
public class EmpLaptopRelation {

    @Autowired
    private LaptopService laptopService;

    @PostMapping("doRelation/{userId}/{laptopId}")
    public ResponseEntity<?> makeRelation(@PathVariable("userId") ObjectId userId,@PathVariable("laptopId") ObjectId laptopId)
    {
        return this.laptopService.makeRekation(userId,laptopId);
    }



}
