package com.samangkar.Samangkar.dto;

import lombok.Data;

@Data
public class AddCardDto {
    
    private Long userId;
    private Long serviceId;
    private Long packageId;
    private int quantity;

    public AddCardDto(Long userId, Long serviceId, Long packageId, int quantity) {
        this.userId = userId;
        this.serviceId = serviceId;
        this.packageId = packageId;
        this.quantity = quantity;
    }
    
}
