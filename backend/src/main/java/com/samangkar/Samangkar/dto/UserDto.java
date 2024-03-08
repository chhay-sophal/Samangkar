package com.samangkar.Samangkar.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String username;
    private String email;
    private String profile;
    private String role;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public UserDto(Long id, String username, String email, String profile, String role, Date createdAt, Date updatedAt, Date deletedAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.profile = profile;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

}
