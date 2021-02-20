package com.andreev.springboot.services;

import com.andreev.springboot.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User getUserByEmail(String email);
}
