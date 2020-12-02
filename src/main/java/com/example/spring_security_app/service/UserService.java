package com.example.spring_security_app.service;

import com.example.spring_security_app.model.User;
import com.example.spring_security_app.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
    List<User> findAll();
}
