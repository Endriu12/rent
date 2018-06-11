package com.perepelitsya.rentservice.api;

import com.perepelitsya.rentservice.model.CompanyHistory;
import com.perepelitsya.rentservice.repository.CompanyHistoryRepository;
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
@RequestMapping("/histories")
public class CompanyHistoryController {

    private final CompanyHistoryRepository companyHistoryRepository;

    @GetMapping
    public List<CompanyHistory> companyHistories() {
        return companyHistoryRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CompanyHistory> getCompanyHistoryById(@PathVariable("id") Long id) {
        Optional<CompanyHistory> company = companyHistoryRepository.findById(id);
        return company.map(val -> new ResponseEntity<>(val, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteHistoryById(@PathVariable("id") Long id) {
        Optional<CompanyHistory> history = companyHistoryRepository.findById(id);
        companyHistoryRepository.delete(history.get());
    }

    @PostMapping
    public ResponseEntity<CompanyHistory> createHistory(@RequestBody CompanyHistory companyHistory) {
        return new ResponseEntity<>(companyHistoryRepository.save(companyHistory), HttpStatus.CREATED);
    }

    @PutMapping
    public CompanyHistory updateHistory(@RequestBody CompanyHistory companyHistory) {
        return companyHistoryRepository.save(companyHistory);
    }
}