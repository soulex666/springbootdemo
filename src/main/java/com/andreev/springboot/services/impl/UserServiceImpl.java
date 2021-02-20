package com.andreev.springboot.services.impl;

import com.andreev.springboot.repositories.UserRepository;
import com.andreev.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        com.andreev.springboot.entities.User myUser = userRepository.findByEmail(s);

        if(myUser != null) {

            User user = new User(myUser.getEmail(), myUser.getPassword(), myUser.getRoles());

            return user;

        }

        throw new UsernameNotFoundException("User not found");
    }

    @Override
    public com.andreev.springboot.entities.User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
