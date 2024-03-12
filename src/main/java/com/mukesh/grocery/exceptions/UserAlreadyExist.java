package com.mukesh.grocery.exceptions;

public class UserAlreadyExist extends RuntimeException {
    public UserAlreadyExist(String format) {
        super(format);
    }
}
