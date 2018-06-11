package com.perepelitsya.rentservice.repository;

import com.perepelitsya.rentservice.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findOneByUsername(String username);
}
