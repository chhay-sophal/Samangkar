package com.samangkar.Samangkar.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class PackageDto {
    
    private Long id;
    private String name;
    private String description;
    private String image;
    private double price;
    private Long shopId;
    private String shopName;
    private List<ServiceDto> services;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public PackageDto(Long id, String name, String description, String image, double price, Long shopId,
            String shopName, List<ServiceDto> services, Date createdAt, Date updatedAt, Date deletedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.shopId = shopId;
        this.shopName = shopName;
        this.services = services;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

}
