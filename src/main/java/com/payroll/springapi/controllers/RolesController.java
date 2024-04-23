package com.payroll.springapi.controllers;

import com.payroll.springapi.entities.Roles;
import com.payroll.springapi.services.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/app/roles")
public class RolesController {

    private RolesService rolesService = null;

    @Autowired
    public RolesController(RolesService rolesService){
        this.rolesService = rolesService;
    }

    @GetMapping(path = "/test")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("I am here");
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Roles>> getAllRoles(){
        return new ResponseEntity<List<Roles>>(rolesService.getRoles(), HttpStatus.FOUND);
    }

    @GetMapping(path = "/privilege/{code}")
    public ResponseEntity<Roles> getRole(@PathVariable String code){
        return new ResponseEntity<Roles>(rolesService.getRole(code), HttpStatus.FOUND);
    }
}
