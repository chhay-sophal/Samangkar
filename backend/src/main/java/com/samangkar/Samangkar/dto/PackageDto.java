package com.samangkar.Samangkar.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class PackageDto {
    
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private double price;
    private ShopDto shop;
    private List<ServiceDto> services;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public PackageDto(Long id, String name, String description, String imageUrl, double price, ShopDto shop,
            List<ServiceDto> services, Date createdAt, Date updatedAt, Date deletedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.shop = shop;
        this.services = services;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

}
