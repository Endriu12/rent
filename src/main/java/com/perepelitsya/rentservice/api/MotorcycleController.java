package com.perepelitsya.rentservice.api;

import com.perepelitsya.rentservice.model.Motorcycle;
import com.perepelitsya.rentservice.repository.MotorcycleRepository;
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
@RequestMapping("/motorcycles")
public class MotorcycleController {

    private final MotorcycleRepository motorcycleRepository;

    @GetMapping
    public List<Motorcycle> motorcycles() {
        return motorcycleRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Motorcycle> getMotorcycleById(@PathVariable("id") Long id) {
        Optional<Motorcycle> motorcycle = motorcycleRepository.findById(id);
        return motorcycle.map(val -> new ResponseEntity<>(val, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteMotorcycleById(@PathVariable("id") Long id) {
        Optional<Motorcycle> motorcycle = motorcycleRepository.findById(id);
        motorcycleRepository.delete(motorcycle.get());
    }

    @PostMapping
    public ResponseEntity<Motorcycle> createBicycle(@RequestBody Motorcycle motorcycle) {
        if (motorcycleRepository.findOneByName(motorcycle.getName()) != null) {
            throw new RuntimeException("Motorcycle already exist");
        }
        return new ResponseEntity<>(motorcycleRepository.save(motorcycle), HttpStatus.CREATED);
    }

    @PutMapping
    public Motorcycle updateMotorcycle(@RequestBody Motorcycle motorcycle) {
        if (motorcycleRepository.findOneByName(motorcycle.getName()) != null
                && motorcycleRepository.findOneByName(motorcycle.getName()).getId()
                != motorcycle.getId()) {
            throw new RuntimeException("Motorcycle already exist");
        }
        return motorcycleRepository.save(motorcycle);
    }
}