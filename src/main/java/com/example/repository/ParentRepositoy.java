package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.carelinkbackend.model.Parent;

public interface ParentRepositoy extends JpaRepository<Parent, Long> {
    
    Optional<Parent> findByUserId(String name);
    

}
