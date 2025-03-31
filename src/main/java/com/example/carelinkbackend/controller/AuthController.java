package com.example.carelinkbackend.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carelinkbackend.dto.requests.LoginRequest;
import com.example.carelinkbackend.dto.requests.RefreshTokenRequest;
import com.example.carelinkbackend.dto.requests.SignupRequest;
import com.example.carelinkbackend.dto.response.JwtResponse;
import com.example.carelinkbackend.dto.response.MessageResponse;
import com.example.carelinkbackend.dto.response.TokenRefreshResponse;
import com.example.carelinkbackend.model.EnumRole;
import com.example.carelinkbackend.model.Nanny;
import com.example.carelinkbackend.model.Parent;
import com.example.carelinkbackend.model.Role;
import com.example.carelinkbackend.model.User;
import com.example.carelinkbackend.repository.UserRepository;
import com.example.carelinkbackend.security.JwtUtil;
import com.example.carelinkbackend.service.UserDetailsImpl;
import com.example.carelinkbackend.service.CustomUserDetailsService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
	AuthenticationManager authenticationManager;

    @Autowired
	JwtUtil jwtUtils;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    


    public AuthController(PasswordEncoder encoder, AuthenticationManager authenticationManager, JwtUtil jwtUtils,
            UserRepository userRepository, CustomUserDetailsService userDetailsService) {
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        
        String jwtToken = jwtUtils.generateJwtToken(authentication);
        String refreshToken = jwtUtils.generateRefreshToken(authentication);
        
        List<String> roles = userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(
            jwtToken,
            userDetails.getId(),
            userDetails.getUsername(),
            userDetails.getEmail(),
            userDetails.getFirstName(),
            userDetails.getLastName(),
            roles
        ));
    }

    @PostMapping("/signup/parent")
    public ResponseEntity<?> registerParent(@Valid @RequestBody SignupRequest signUpRequest) {
        return registerUser(signUpRequest, EnumRole.ROLE_PARENT, Parent.class);
    }

    @PostMapping("/signup/nanny")
    public ResponseEntity<?> registerNanny(@Valid @RequestBody SignupRequest signUpRequest) {
        return registerUser(signUpRequest, EnumRole.ROLE_NANNY, Nanny.class);
    }

     @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();
        
        if (!jwtUtils.validateJwtToken(refreshToken)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Refresh token is invalid!"));
        }
        
        String username = jwtUtils.getUserNameFromJwtToken(refreshToken);
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
        
        UsernamePasswordAuthenticationToken authentication = 
            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        
        String newToken = jwtUtils.generateJwtToken(authentication);
        
        return ResponseEntity.ok(new TokenRefreshResponse(newToken, refreshToken));
    }


    private ResponseEntity<?> registerUser(SignupRequest signUpRequest, EnumRole defaultRole, Class<? extends User> userClass) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Email is already in use!"));
        }

        try {
            User user = userClass.getDeclaredConstructor().newInstance();
            user.setUsername(signUpRequest.getUsername());
            user.setEmail(signUpRequest.getEmail());
            user.setPassword(encoder.encode(signUpRequest.getPassword()));
            user.setFirstName(signUpRequest.getFirstName());
            user.setLastName(signUpRequest.getLastName());

            Set<EnumRole> roles = new HashSet<>();
            EnumRole roleToAssign = signUpRequest.getRole() != null ? signUpRequest.getRole() : defaultRole;
            roles.add(roleToAssign);
            user.setRoles(roles);

            userRepository.save(user);

            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        } catch (Exception e) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: " + e.getMessage()));
        }
    }
    

}
