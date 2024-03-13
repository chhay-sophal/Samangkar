package com.samangkar.Samangkar.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ServiceDto {
    
    private Long id;
    private String name;
    private String description;
    private double unitPrice;
    private boolean isTrending;
    private Long shopId;
    private String image;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public ServiceDto(Long id, String name, String description, double unitPrice, boolean isTrending, Long shopId,
            String image, Date createdAt, Date updatedAt, Date deletedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.isTrending = isTrending;
        this.shopId = shopId;
        this.image = image;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

}
