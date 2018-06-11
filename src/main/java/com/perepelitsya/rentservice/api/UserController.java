package com.perepelitsya.rentservice.api;


import com.perepelitsya.rentservice.model.AppUser;
import com.perepelitsya.rentservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public List<AppUser> users() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable("id") Long id) {
        Optional<AppUser> user = userRepository.findById(id);
        return user.map(appUser -> new ResponseEntity<>(appUser, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUserById(@PathVariable("id") Long id) {
        Optional<AppUser> appUser = userRepository.findById(id);
        userRepository.delete(appUser.get());
    }

    @PostMapping
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser appUser) {
        if (userRepository.findOneByUsername(appUser.getUsername()) != null) {
            throw new RuntimeException("Username already exist");
        }
        return new ResponseEntity<>(userRepository.save(appUser), HttpStatus.CREATED);
    }

    @PutMapping
    public AppUser updateUser(@RequestBody AppUser appUser) {
        if (userRepository.findOneByUsername(appUser.getUsername()) != null
                && userRepository.findOneByUsername(appUser.getUsername()).getId()
                != appUser.getId()) {
            throw new RuntimeException("Username already exist");
        }
        return userRepository.save(appUser);
    }
}