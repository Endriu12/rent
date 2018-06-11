package com.perepelitsya.rentservice.repository;

import com.perepelitsya.rentservice.model.RentData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentDataRepository extends JpaRepository<RentData, Long> {
}
