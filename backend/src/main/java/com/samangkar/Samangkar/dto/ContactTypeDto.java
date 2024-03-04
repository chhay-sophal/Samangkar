package com.samangkar.Samangkar.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ContactTypeDto {
    
    private Long id;
    private String platForm;
    private Date createdAt;
    private Date updatedAt;

    public ContactTypeDto(Long id, String platForm, Date createdAt, Date updatedAt) {
        this.id = id;
        this.platForm = platForm;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
