package com.samangkar.Samangkar.dto;

import lombok.Data;

@Data
public class AddServiceDto {
    
    private String serviceName;
    private String description;
    private double unitPrice;
    private Long shopId;

    public AddServiceDto(String serviceName, String description, double unitPrice, Long shopId) {
        this.serviceName = serviceName;
        this.description = description;
        this.unitPrice = unitPrice;
        this.shopId = shopId;
    }

}
