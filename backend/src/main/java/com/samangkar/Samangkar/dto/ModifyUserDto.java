package com.samangkar.Samangkar.dto;

import lombok.Data;

@Data
public class ModifyUserDto {
    private String username;
    private String email;

    public ModifyUserDto(String username, String email) {
        this.username = username;
        this.email = email;
    }

}
