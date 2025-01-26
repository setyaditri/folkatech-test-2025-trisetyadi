package com.example.springboot_restapi_belanja.controller;

import com.example.springboot_restapi_belanja.dto.LoginRequestDTO;
import com.example.springboot_restapi_belanja.entity.User;
import com.example.springboot_restapi_belanja.service.UserService;
import com.example.springboot_restapi_belanja.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User registeredUser = userService.register(user);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDTO loginRequest) {
        User user = userService.findByEmail(loginRequest.getEmail());
        
        if (user == null || !userService.validatePassword(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
        return ResponseEntity.ok().body("Bearer " + token);
    }

}