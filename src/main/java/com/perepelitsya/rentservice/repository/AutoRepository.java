package com.perepelitsya.rentservice.repository;

import com.perepelitsya.rentservice.model.Auto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoRepository extends JpaRepository<Auto, Long> {
    Auto findOneByName(String name);
}
