package com.pm.authservice.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pm.authservice.model.User;
import com.pm.authservice.respository.UserRepository;

import lombok.Data;

@Service
@Data
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
}
