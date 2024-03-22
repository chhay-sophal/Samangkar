package com.samangkar.Samangkar.dto;

import java.util.Date;

import lombok.Data;

@Data
public class RequestDto {
    private Long id;
    private Long userId;
    private String userUsername;
    private String purpose;
    private String description;
    private Date createdAt;

    public RequestDto(Long id, Long userId, String userUsername, String purpose, String description, Date createdAt) {
        this.id = id;
        this.userId = userId;
        this.userUsername = userUsername;
        this.purpose = purpose;
        this.description = description;
        this.createdAt = createdAt;
    }

}
