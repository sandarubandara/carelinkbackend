package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.carelinkbackend.model.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
  

}
