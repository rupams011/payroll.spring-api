package com.payroll.springapi.repositories;

import com.payroll.springapi.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRespository extends JpaRepository<Roles, Integer> {

}
