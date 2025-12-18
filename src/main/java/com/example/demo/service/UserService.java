package com.example.demo.service;

import com.example.demo.entity.UserEntity;

public interface UserService {

UserEntity register(String email, String password, String role);

UserEntity login(String email, String password);

UserEntity getByEmail(String email);
}