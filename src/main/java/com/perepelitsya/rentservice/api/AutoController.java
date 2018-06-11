package com.perepelitsya.rentservice.api;

import com.perepelitsya.rentservice.model.Auto;
import com.perepelitsya.rentservice.repository.AutoRepository;
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
@RequestMapping("/autos")
public class AutoController {

    private final AutoRepository autoRepository;

    @GetMapping
    public List<Auto> autos() {
        return autoRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Auto> getAutoById(@PathVariable("id") Long id) {
        Optional<Auto> auto = autoRepository.findById(id);
        return auto.map(val -> new ResponseEntity<>(val, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteAutoById(@PathVariable("id") Long id) {
        Optional<Auto> auto = autoRepository.findById(id);
        autoRepository.delete(auto.get());
    }

    @PostMapping
    public ResponseEntity<Auto> createAuto(@RequestBody Auto auto) {
        if (autoRepository.findOneByName(auto.getName()) != null) {
            throw new RuntimeException("Auto already exist");
        }
        return new ResponseEntity<>(autoRepository.save(auto), HttpStatus.CREATED);
    }

    @PutMapping
    public Auto updateAuto(@RequestBody Auto auto) {
        if (autoRepository.findOneByName(auto.getName()) != null
                && autoRepository.findOneByName(auto.getName()).getId()
                != auto.getId()) {
            throw new RuntimeException("Auto already exist");
        }
        return autoRepository.save(auto);
    }
}