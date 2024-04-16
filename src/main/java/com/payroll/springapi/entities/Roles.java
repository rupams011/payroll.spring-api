package com.payroll.springapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "role", unique = true, nullable = false)
    private String role;

    @Column(name = "privilege")
    private String privilege;

    @Column(name = "description")
    private String description;
}
