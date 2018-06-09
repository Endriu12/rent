package com.perepelitsya.rentbicycles.repository;

import com.perepelitsya.rentbicycles.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
}
