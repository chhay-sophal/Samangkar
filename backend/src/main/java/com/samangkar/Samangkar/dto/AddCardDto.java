package com.samangkar.Samangkar.dto;

import lombok.Data;

@Data
public class AddCardDto {
    
    private Long userId;
    private Long serviceId;
    private int quantity;

    public AddCardDto(Long userId, Long serviceId, int quantity) {
        this.userId = userId;
        this.serviceId = serviceId;
        this.quantity = quantity;
    }
    
}
