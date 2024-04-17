package com.payroll.springapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "credentials")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SecondaryTable(name = "roles", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))     //To take only the column required
public class UserCredentials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "username")
    private String username;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role", referencedColumnName = "privilege")
    private Roles role;*/
    ////Above code gets entire Roles object, using secondary table to get only required column
    @Column(name = "privilege", table = "roles")
    private String role;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;
}
