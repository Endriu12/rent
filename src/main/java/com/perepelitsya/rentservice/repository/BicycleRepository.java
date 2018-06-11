package com.perepelitsya.rentservice.repository;

import com.perepelitsya.rentservice.model.Bicycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BicycleRepository extends JpaRepository<Bicycle, Long> {
    Bicycle findOneByName(String name);
}
