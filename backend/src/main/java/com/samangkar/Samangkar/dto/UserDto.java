package com.samangkar.Samangkar.dto;

import lombok.Data;

@Data
public class UserDto {

    private String id;
    private String username;
    private String email;
    private String profile;

    public UserDto(Long id, String username, String email, String profile) {
        this.id = id.toString();
        this.username = username;
        this.email = email;
        this.profile = profile;
    }
}
