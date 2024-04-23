package com.payroll.springapi.services;

import com.payroll.springapi.entities.UserCredentials;
import com.payroll.springapi.repositories.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialsService {

    private UserCredentialsRepository userCredentialsRepository = null;

    @Autowired
    public UserCredentialsService(UserCredentialsRepository userCredentialsRepository){
        this.userCredentialsRepository = userCredentialsRepository;
    }

    public UserCredentials getUserCredential(String username){
        return userCredentialsRepository.findByUsername(username).orElseThrow();
    }
}
