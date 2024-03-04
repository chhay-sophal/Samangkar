package com.samangkar.Samangkar.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserDto {

    private Long userId;
    private String username;
    private String email;
    private String profile;
    private Date createdAt;
    private Date updatedAt;

    public UserDto(Long userId, String username, String email, String profile, Date createdAt, Date updatedAt) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.profile = profile;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
