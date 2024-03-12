package com.mukesh.grocery.services;

import com.mukesh.grocery.model.User;

public interface AccountService {

    User createAccount(User user);

    User findByUserName(String userName);

    User loginAccount(User user);
}
