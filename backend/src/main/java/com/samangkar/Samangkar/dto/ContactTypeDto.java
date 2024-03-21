package com.samangkar.Samangkar.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ContactTypeDto {
    
    private Long id;
    private String platform;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public ContactTypeDto(Long id, String platform, Date createdAt, Date updatedAt, Date deletedAt) {
        this.id = id;
        this.platform = platform;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

}
