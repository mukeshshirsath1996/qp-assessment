package com.mukesh.grocery.services;

import com.mukesh.grocery.model.User;

public interface UserService {

    User findByUserId(long userId);
}
