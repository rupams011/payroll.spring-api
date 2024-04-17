package com.payroll.springapi.services;

import com.payroll.springapi.entities.Roles;
import com.payroll.springapi.repositories.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesService {

    private RolesRepository rolesRepository = null;

    @Autowired
    public RolesService(RolesRepository rolesRepository){
        this.rolesRepository = rolesRepository;
    }

    public List<Roles> getRoles() {
        return rolesRepository.findAll();
    }

    public Roles getRole(String privilege) {
        return rolesRepository.findByPrivilege(privilege);
    }
}
