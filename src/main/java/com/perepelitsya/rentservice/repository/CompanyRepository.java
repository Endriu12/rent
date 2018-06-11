package com.perepelitsya.rentservice.repository;

import com.perepelitsya.rentservice.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findOneByCompanyName(String companyName);
}
