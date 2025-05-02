package com.pm.authservice.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pm.authservice.dto.LoginRequestDTO;
import com.pm.authservice.utils.JwtUtil;

import io.jsonwebtoken.JwtException;
import lombok.Data;

@Service
@Data
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public Optional<String> authenticate(LoginRequestDTO loginRequest) {

        Optional<String> token = userService.findByEmail(loginRequest.getEmail())
                                            .filter(u -> passwordEncoder.matches(loginRequest.getPassword(),u.getPassword()))
                                            .map(u -> jwtUtil.generateToken(u.getEmail(),u.getRole()));
        return token;       
    }

    public boolean validateToken(String token) {
        try {
            jwtUtil.validateToken(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
    
}
