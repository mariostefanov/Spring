package com.example.spring_data_intro.services.impl;

import com.example.spring_data_intro.models.User;
import com.example.spring_data_intro.repositories.UserRepository;
import com.example.spring_data_intro.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {

    }
}
