package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Meds;
import com.example.demo.Repository.MedRepository;

@RestController
@RequestMapping("/api/home")
public class MedRestController {

    private final MedRepository medRepository;

    @Autowired
    public MedRestController(MedRepository medRepository) {
        this.medRepository = medRepository;
    }

    @GetMapping("/meds")
    public Iterable<Meds> getAllMeds() {
        return medRepository.findAll();
    }

    @PostMapping("/meds")
    public ResponseEntity<String> addNewMeds(@RequestBody Meds newMeds) {
        medRepository.save(newMeds);
        return ResponseEntity.status(HttpStatus.CREATED).body("New meds " + newMeds.getName() + " have been added successfully");
    }

    @DeleteMapping("/meds/{name}")
    public ResponseEntity<String> deleteMed(@PathVariable String name) {
        Optional<Meds> medDetails = medRepository.findByName(name);
        if (medDetails.isPresent()) {
            Meds medItemDetails = medDetails.get();
            medRepository.delete(medItemDetails);
            return ResponseEntity.ok("Medicine " + name + " has been deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/meds/{name}")
    public ResponseEntity<Meds> searchMed(@PathVariable String name) {
        Optional<Meds> medDetails = medRepository.findByName(name);
        return medDetails.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/meds/{name}")
    public ResponseEntity<String> updateMed(@PathVariable String name, @RequestParam Long amount) {
        Optional<Meds> medDetails = medRepository.findByName(name);
        if (medDetails.isPresent()) {
            Meds medToUpdate = medDetails.get();
            medToUpdate.setAmount(amount);
            medRepository.save(medToUpdate);
            return ResponseEntity.ok("Medicine " + name + " has been updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
