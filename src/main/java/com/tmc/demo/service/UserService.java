package com.tmc.demo.service;

import com.tmc.demo.model.User;
import com.tmc.demo.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User save(UserRegistrationDto registrationDto);
    
}