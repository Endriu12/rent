package com.perepelitsya.rentservice.repository;

import com.perepelitsya.rentservice.model.CompanyHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyHistoryRepository extends JpaRepository<CompanyHistory, Long> {
}
