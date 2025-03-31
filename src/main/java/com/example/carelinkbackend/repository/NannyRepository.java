package com.example.carelinkbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.carelinkbackend.model.Nanny;



public interface NannyRepository extends JpaRepository<Nanny, Long> {
    
   
    //List<Nanny> findAll();
    // Nanny findByUserId(long currentUserId);
    // Nanny findNannyById(long nannyId);
    // Optional<Nanny> findByNannyByUserId(long userId);

}
