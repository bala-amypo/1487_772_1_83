package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

@Autowired
UserRepository userRepository;

@Override
public UserEntity register(String email, String password, String role) {

// email must be unique
if (userRepository.findByEmail(email) != null) {
throw new RuntimeException("Email already exists");
}

// role required
if (role == null || role.isEmpty()) {
throw new RuntimeException("Role is required");
}

UserEntity user = new UserEntity();
user.setEmail(email);
user.setPassword(password);
user.setRole(role);

return userRepository.save(user);
}

@Override
public UserEntity login(String email, String password) {
UserEntity user = userRepository.findByEmail(email);

if (user == null || !user.getPassword().equals(password)) {
throw new RuntimeException("Invalid email or password");
}

return user;
}

@Override
public UserEntity getByEmail(String email) {
return userRepository.findByEmail(email);
}
}