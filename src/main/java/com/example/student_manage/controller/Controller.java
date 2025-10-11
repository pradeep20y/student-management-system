package com.example.student_manage.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.student_manage.dto.LoginResponse;
import com.example.student_manage.dto.UserRequest;
import com.example.student_manage.dto.UserResponse;
import com.example.student_manage.jwt.JwtUtils;
import com.example.student_manage.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class Controller {

    @Autowired
    public UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("ping")
    public String ping() {
        return "pong";
    }

    @PostMapping("/signup")
    public UserResponse signup(@RequestBody UserRequest userRequest) {
        return userService.signup(userRequest);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody UserRequest loginRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUser(),
                            loginRequest.getPassword()));
        } catch (AuthenticationException e) {
            // ✅ Print only exception type and message, no stack trace
            System.out.println("❌ Authentication failed: " + e.getClass().getSimpleName());
            System.out.println("🪵 Message: " + e.getMessage());

            // Optionally, return the message in response
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", e.getMessage()));
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String jwtToken = jwtUtils.generateTokenFromUsername(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        LoginResponse response = new LoginResponse(jwtToken, userDetails.getUsername(), roles);

        return ResponseEntity.ok(response);
    }

}