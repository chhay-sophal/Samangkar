package com.samangkar.Samangkar.dto;

import lombok.Data;

@Data
public class ContactTypeDto {
    
    private Long id;
    private String platForm;

    public ContactTypeDto(Long id, String platForm) {
        this.id = id;
        this.platForm = platForm;
    }
}
