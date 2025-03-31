package com.example.carelinkbackend.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.InheritanceType;

@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = "username"),
                    @UniqueConstraint(columnNames = "email")})
public class User {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @Size(max = 50)
    private String username;

    @NotBlank
	@Size(max = 50)
	@Email
	private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    private boolean active = true;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "user_roles", 
	           joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<EnumRole> roles = new HashSet<>();

    public User() {
    }


    public User(String firstName, String lastName, @NotBlank @Size(max = 50) String username,
            @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 120) String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    


    


    public User(String firstName, String lastName, @NotBlank @Size(max = 50) String username,
            @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 120) String password, boolean active,
            Set<EnumRole> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }


    public User(String firstName, String lastName, @NotBlank @Size(max = 50) String username,
            @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 120) String password,
            Set<EnumRole> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public Set<EnumRole> getRoles() {
        return roles;
    }


    public void setRoles(Set<EnumRole> roles) {
        this.roles = roles;
    }

   
    
 
    
}
