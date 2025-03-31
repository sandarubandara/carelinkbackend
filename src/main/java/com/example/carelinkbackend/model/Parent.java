package com.example.carelinkbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "parent")
public class Parent extends User {

   
    public Parent() {
    }

    public Parent(String firstName, String lastName, String username, String email, String password) {
        super(firstName, lastName, username, email, password);
    }
  
}
