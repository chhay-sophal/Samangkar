package com.samangkar.Samangkar.dto;

import lombok.Data;

@Data
public class AddCardDto {
    
    private Long userId;
    private Long serviceId;
    private double total;
    private int quantity;

    public AddCardDto(Long userId, Long serviceId, double total, int quantity) {
        this.userId = userId;
        this.serviceId = serviceId;
        this.total = total;
        this.quantity = quantity;
    }
    
}
