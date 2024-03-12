package com.mukesh.grocery.services;

import com.mukesh.grocery.enums.Role;
import com.mukesh.grocery.exceptions.LoginFailedException;
import com.mukesh.grocery.exceptions.UserAlreadyExist;
import com.mukesh.grocery.exceptions.UserNotFound;
import com.mukesh.grocery.model.User;
import com.mukesh.grocery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createAccount(User user) {
        if(!userRepository.existsByUserName(user.getUserName())){
            user.setRole(Role.USER.name());
            return userRepository.save(user);
        }
        throw new UserAlreadyExist(String.format("%s already exists",user.getUserName()));
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(()->new UserNotFound(String.format("User not found with %s",userName)));
    }

    @Override
    public User loginAccount(User user) {
        User byUserName = findByUserName(user.getUserName());
        if (byUserName.getUserPassword().equals(user.getUserPassword())){
            return byUserName;
        }else {
            throw new LoginFailedException(String.format("Username or password is invalid"));
        }
    }
}
