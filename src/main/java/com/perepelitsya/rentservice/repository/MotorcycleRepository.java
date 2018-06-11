package com.perepelitsya.rentservice.repository;

import com.perepelitsya.rentservice.model.Motorcycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotorcycleRepository extends JpaRepository<Motorcycle, Long> {
    Motorcycle findOneByName(String name);
}