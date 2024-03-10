package com.samangkar.Samangkar.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private String role;
    private String username;
    private String email;
    private String password;

    public RegisterDto(String role, String username, String email, String password) {
        this.role = role;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    
}
