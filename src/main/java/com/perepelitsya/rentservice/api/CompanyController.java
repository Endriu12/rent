package com.perepelitsya.rentservice.api;

import com.perepelitsya.rentservice.model.Company;
import com.perepelitsya.rentservice.repository.CompanyRepository;
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
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyRepository companyRepository;

    @GetMapping
    public List<Company> companies() {
        return companyRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable("id") Long id) {
        Optional<Company> company = companyRepository.findById(id);
        return company.map(val -> new ResponseEntity<>(val, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUserById(@PathVariable("id") Long id) {
        Optional<Company> Company = companyRepository.findById(id);
        companyRepository.delete(Company.get());
    }

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        if (companyRepository.findOneByCompanyName(company.getCompanyName()) != null) {
            throw new RuntimeException("Company name already exist");
        }
        return new ResponseEntity<>(companyRepository.save(company), HttpStatus.CREATED);
    }

    @PutMapping
    public Company updateCompany(@RequestBody Company company) {
        if (companyRepository.findOneByCompanyName(company.getCompanyName()) != null
                && companyRepository.findOneByCompanyName(company.getCompanyName()).getId()
                != company.getId()) {
            throw new RuntimeException("Company name already exist");
        }
        return companyRepository.save(company);
    }
}