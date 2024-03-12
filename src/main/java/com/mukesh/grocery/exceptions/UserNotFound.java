package com.mukesh.grocery.exceptions;

public class UserNotFound extends RuntimeException {
    public UserNotFound(String format) {
        super(format);
    }
}
