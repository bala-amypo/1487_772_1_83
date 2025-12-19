package com.example.demo.service;

import com.example.demo.entity.UserModel;

public interface UserService {

UserModel register(String email, String password, String role);

UserModel login(String email, String password);

UserModel getByEmail(String email);
}