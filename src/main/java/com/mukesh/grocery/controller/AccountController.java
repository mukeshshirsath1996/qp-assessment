package com.mukesh.grocery.controller;

import com.mukesh.grocery.model.Product;
import com.mukesh.grocery.model.User;
import com.mukesh.grocery.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /*Create new account*/
    @PostMapping("/create")
    public ResponseEntity<User> createAccount(@RequestBody User user){
        return ResponseEntity.ok(accountService.createAccount(user));
    }

    /*login*/
    @PostMapping("/login")
    public ResponseEntity<User> loginAccount(@RequestBody User user){
        return ResponseEntity.ok(accountService.loginAccount(user));
    }

}
