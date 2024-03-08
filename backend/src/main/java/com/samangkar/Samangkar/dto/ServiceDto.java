package com.samangkar.Samangkar.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ServiceDto {
    
    private Long id;
    private String name;
    private String description;
    private double unitPrice;
    private ShopDto shop;
    private Date createdAt;
    private Date updatedAt;

    public ServiceDto(Long id, String name, String description, double unitPrice, ShopDto shop,
            Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.shop = shop;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
