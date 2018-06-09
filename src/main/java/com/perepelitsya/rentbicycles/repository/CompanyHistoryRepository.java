package com.perepelitsya.rentbicycles.repository;

import com.perepelitsya.rentbicycles.model.CompanyHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyHistoryRepository extends JpaRepository<CompanyHistory, Long> {
}
