package com.perepelitsya.rentbicycles.repository;

import com.perepelitsya.rentbicycles.model.RentData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentDataRepository extends JpaRepository<RentData, Long> {
}
