package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.UserModel;
import com.example.demo.service.UserService;

@RestController
public class AuthController {

@Autowired
UserService userService;

// POST /register
@PostMapping("/register")
public UserModel register(@RequestParam String email,
@RequestParam String password,
@RequestParam String role) {
return userService.register(email, password, role);
}

// POST /login
@PostMapping("/login")
public UserModel login(@RequestParam String email,
@RequestParam String password) {
return userService.login(email, password);
}
}