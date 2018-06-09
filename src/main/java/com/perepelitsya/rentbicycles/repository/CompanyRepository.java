package com.perepelitsya.rentbicycles.repository;

import com.perepelitsya.rentbicycles.model.AppUser;
import com.perepelitsya.rentbicycles.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
