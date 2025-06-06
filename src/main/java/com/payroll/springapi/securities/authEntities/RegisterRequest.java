package com.payroll.springapi.securities.authEntities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

//    private String firstName;
    private String email;
    private String username;
    private String password;
    private String role;
}
