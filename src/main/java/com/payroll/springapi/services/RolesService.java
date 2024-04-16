package com.payroll.springapi.services;

import com.payroll.springapi.entities.Roles;
import com.payroll.springapi.repositories.RolesRespository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesService {

    private RolesRespository rolesRespository;


    public List<Roles> getRoles() {
        return rolesRespository.findAll();
    }
}
