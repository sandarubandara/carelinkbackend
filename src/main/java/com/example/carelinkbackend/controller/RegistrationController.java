package com.example.carelinkbackend.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carelinkbackend.dto.requests.RegisterRequest;
import com.example.carelinkbackend.dto.response.MessageResponse;
import com.example.carelinkbackend.model.EnumRole;
import com.example.carelinkbackend.model.Nanny;
import com.example.carelinkbackend.model.Role;
import com.example.carelinkbackend.model.User;
import com.example.carelinkbackend.repository.RoleRepository;
import com.example.carelinkbackend.repository.UserRepository;
import com.example.carelinkbackend.security.JwtUtil;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/carelink")
public class RegistrationController {

	/*

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
	AuthenticationManager authenticationManager;

    @Autowired
	JwtUtil jwtUtils;



   @PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
		

			if (userRepository.existsByUsername(registerRequest.getUsername())) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: The username is already exist!"));			
			}
			if (userRepository.existsByEmail(registerRequest.getEmail())) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: The email is already exist!"));
			}


			// Create new user's account
			User _user = new User(registerRequest.getUsername(),registerRequest.getEmail(),encoder.encode(registerRequest.getPassword()));

			Set<String> stringRoles = registerRequest.getRole();
			Set<Role> roles = new HashSet<>();

			if (stringRoles == null) {
				Role userRole = roleRepository.findByName(EnumRole.ROLE_PARENT)
						.orElseThrow(() -> new RuntimeException("Error: The role is not found."));
				roles.add(userRole);
			} else {
				stringRoles.forEach(role -> {
					switch (role) {
					case "ROLE_ADMIN":
						Role roleAdmin = roleRepository.findByName(EnumRole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: The role is not found."));
						roles.add(roleAdmin);
						break;
					case "ROLE_NANNY":
						Role roleNanny = roleRepository.findByName(EnumRole.ROLE_NANNY)
								.orElseThrow(() -> new RuntimeException("Error: The role is not found."));
						roles.add(roleNanny);
						break;
					default:
						Role roleParent = roleRepository.findByName(EnumRole.ROLE_PARENT)
								.orElseThrow(() -> new RuntimeException("Error: The role is not found."));
						roles.add(roleParent);
					}
				});
			}

			_user.setRoles(roles);
			userRepository.save(_user);

			return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
			//return new ResponseEntity<>(_user, HttpStatus.CREATED);
	}

	*/
}
