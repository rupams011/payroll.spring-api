package com.payroll.springapi.repositories;

import com.payroll.springapi.entities.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Long> {
    public Optional<UserCredentials> findByUsername(String username);
}
