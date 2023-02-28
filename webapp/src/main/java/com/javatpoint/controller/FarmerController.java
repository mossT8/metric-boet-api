package com.javatpoint.controller;

import com.javatpoint.models.Farmer;
import com.javatpoint.repository.farmer.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/farmers")
public class FarmerController {
    @Autowired
    FarmerRepository farmerRepository;

    @GetMapping
    public ResponseEntity<List<Farmer>> getAllFarmers() {
        List<Farmer> farmers = farmerRepository.findAll();
        return new ResponseEntity<>(farmers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Farmer> createFarmer(@RequestBody Farmer farmer) {
        Farmer savedFarmer = farmerRepository.save(farmer);
        return new ResponseEntity<>(savedFarmer, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Farmer> getFarmerById(@PathVariable Long id) {
        Optional<Farmer> farmer = farmerRepository.findById(id);
        if (farmer.isPresent()) {
            return new ResponseEntity<>(farmer.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Farmer> updateFarmer(@PathVariable Long id, @RequestBody Farmer farmer) {
        Optional<Farmer> farmerData = farmerRepository.findById(id);
        if (farmerData.isPresent()) {
            Farmer updatedFarmer = farmerData.get();
            updatedFarmer.setName(farmer.getName());
            updatedFarmer.setAddress(farmer.getAddress());
            updatedFarmer.setPhoneNumber(farmer.getPhoneNumber());
            updatedFarmer.setEmail(farmer.getEmail());
            return new ResponseEntity<>(farmerRepository.save(updatedFarmer), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteFarmer(@PathVariable Long id) {
        try {
            farmerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
