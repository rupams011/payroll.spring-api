package com.payroll.springapi.repositories;

import com.payroll.springapi.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
    public Roles findByPrivilege(String privilege);
}
