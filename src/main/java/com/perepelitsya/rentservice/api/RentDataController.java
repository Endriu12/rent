package com.perepelitsya.rentservice.api;

import com.perepelitsya.rentservice.model.RentData;
import com.perepelitsya.rentservice.repository.RentDataRepository;
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
@RequestMapping("/rent-data")
public class RentDataController {

    private final RentDataRepository rentDataRepository;

    @GetMapping
    public List<RentData> getAllRentData() {
        return rentDataRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RentData> getRentDataById(@PathVariable("id") Long id) {
        Optional<RentData> rentData = rentDataRepository.findById(id);
        return rentData.map(val -> new ResponseEntity<>(val, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteRentDataById(@PathVariable("id") Long id) {
        Optional<RentData> rentData = rentDataRepository.findById(id);
        rentDataRepository.delete(rentData.get());
    }

    @PostMapping
    public ResponseEntity<RentData> createRentData(@RequestBody RentData rentData) {
        return new ResponseEntity<>(rentDataRepository.save(rentData), HttpStatus.CREATED);
    }

    @PutMapping
    public RentData updateRentData(@RequestBody RentData rentData) {
        return rentDataRepository.save(rentData);
    }
}