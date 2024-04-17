package com.payroll.springapi.controllers;

import com.payroll.springapi.entities.UserCredentials;
import com.payroll.springapi.services.UserCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/credentials")
public class UserCredentialsController {

    private UserCredentialsService userCredentialsService = null;

    @Autowired
    public UserCredentialsController(UserCredentialsService userCredentialsService){
        this.userCredentialsService = userCredentialsService;
    }

    @GetMapping(path = "/{username}")
    public ResponseEntity<UserCredentials> getCredentials(@PathVariable String username){
        return new ResponseEntity<UserCredentials>(userCredentialsService.getUserCredential(username), HttpStatus.FOUND);
    }
}
