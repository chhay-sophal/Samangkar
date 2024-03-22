package com.samangkar.Samangkar.dto;

import lombok.Data;

@Data
public class SendRequestDto {
    
    private Long userId;
    private String purpose;
    private String descriptions;
    
    public SendRequestDto(Long userId, String purpose, String descriptions) {
        this.userId = userId;
        this.purpose = purpose;
        this.descriptions = descriptions;
    }
    
}
