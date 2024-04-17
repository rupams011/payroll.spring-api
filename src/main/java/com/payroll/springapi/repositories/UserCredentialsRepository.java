package com.payroll.springapi.repositories;

import com.payroll.springapi.entities.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Long> {
    public UserCredentials findByUsername(String username);
}
