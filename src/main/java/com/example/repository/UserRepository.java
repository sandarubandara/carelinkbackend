package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.carelinkbackend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  
    Boolean existsByEmail(String email);

}
