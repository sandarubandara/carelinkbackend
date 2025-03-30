package com.example.carelinkbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.carelinkbackend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  
    User findByUsername(String username);
    boolean existsByUsername(String username);

}
