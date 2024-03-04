package com.samangkar.Samangkar.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ServiceDto {
    
    private Long serviceId;
    private String serviceName;
    private String description;
    private double unitPrice;
    private ShopDto shop;
    private Date createdAt;
    private Date updatedAt;

    public ServiceDto(Long serviceId, String serviceName, String description, double unitPrice, ShopDto shop,
            Date createdAt, Date updatedAt) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.description = description;
        this.unitPrice = unitPrice;
        this.shop = shop;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
