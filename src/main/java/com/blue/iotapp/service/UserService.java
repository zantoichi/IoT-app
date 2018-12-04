package com.blue.iotapp.service;

import com.blue.iotapp.model.User;
import com.blue.iotapp.payload.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByEmail(String email);
    User save(UserRegistrationDto registration);
}
