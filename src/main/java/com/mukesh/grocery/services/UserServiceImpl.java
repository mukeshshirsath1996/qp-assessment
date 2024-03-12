package com.mukesh.grocery.services;

import com.mukesh.grocery.exceptions.UserNotFound;
import com.mukesh.grocery.model.User;
import com.mukesh.grocery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUserId(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound(String.format("User ID %s not found", userId)));
    }
}
