package com.perepelitsya.rentservice.api;

import com.perepelitsya.rentservice.model.Bicycle;
import com.perepelitsya.rentservice.repository.BicycleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bicycles")
public class BicycleController {

    private final BicycleRepository bicycleRepository;

    @GetMapping
    public List<Bicycle> bicycles() {
        return bicycleRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Bicycle> getBicycleById(@PathVariable("id") Long id) {
        Optional<Bicycle> bicycle = bicycleRepository.findById(id);
        return bicycle.map(val -> new ResponseEntity<>(val, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteBicycleById(@PathVariable("id") Long id) {
        Optional<Bicycle> bicycle = bicycleRepository.findById(id);
        bicycleRepository.delete(bicycle.get());
    }

    @PostMapping
    public ResponseEntity<Bicycle> createBicycle(@RequestBody Bicycle bicycle) {
        if (bicycleRepository.findOneByName(bicycle.getName()) != null) {
            throw new RuntimeException("Bicycle already exist");
        }
        return new ResponseEntity<>(bicycleRepository.save(bicycle), HttpStatus.CREATED);
    }

    @PutMapping
    public Bicycle updateBicycle(@RequestBody Bicycle bicycle) {
        if (bicycleRepository.findOneByName(bicycle.getName()) != null
                && bicycleRepository.findOneByName(bicycle.getName()).getId()
                != bicycle.getId()) {
            throw new RuntimeException("Bicycle already exist");
        }
        return bicycleRepository.save(bicycle);
    }
}