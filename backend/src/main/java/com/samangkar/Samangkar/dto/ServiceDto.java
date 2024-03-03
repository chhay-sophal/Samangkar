package com.samangkar.Samangkar.dto;

import lombok.Data;

@Data
public class ServiceDto {
    
    private Long serviceId;
    private String serviceName;
    private String description;
    private double unitPrice;
    private Long shopId;

    public ServiceDto(Long serviceId, String serviceName, String description, double unitPrice, Long shopId) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.description = description;
        this.unitPrice = unitPrice;
        this.shopId = shopId;
    }

}
