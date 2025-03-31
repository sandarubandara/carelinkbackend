package com.example.carelinkbackend.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "nanny")
public class Nanny extends User {
    
   

    @OneToMany(mappedBy = "nanny", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	// @JsonIgnore
	public Set<Rating> rating = new HashSet<>();

    public Nanny() {
    }

    public Nanny(String firstName, String lastName, String username, String email, String password) {
        super(firstName, lastName, username, email, password);
    }

    public Nanny(String firstName, String lastName, String username, String email, String password, Set<Rating> rating) {
        super(firstName, lastName, username, email, password);
        this.rating = rating;
    }
    

    public Set<Rating> getRating() {
        return rating;
    }

    public void setRating(Set<Rating> rating) {
        this.rating = rating;
    }

    


}
