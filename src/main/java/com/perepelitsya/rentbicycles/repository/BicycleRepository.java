package com.perepelitsya.rentbicycles.repository;

import com.perepelitsya.rentbicycles.model.Bicycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BicycleRepository extends JpaRepository<Bicycle, Long> {
}
