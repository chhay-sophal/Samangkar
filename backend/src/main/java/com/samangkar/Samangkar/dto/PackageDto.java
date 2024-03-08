package com.samangkar.Samangkar.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class PackageDto {
    
    private Long packageId;
    private String packageName;
    private String description;
    private Long shopId;
    private List<ServiceDto> services;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public PackageDto(Long packageId, String packageName, String description, Long shopId, List<ServiceDto> services,
            Date createdAt, Date updatedAt, Date deletedAt) {
        this.packageId = packageId;
        this.packageName = packageName;
        this.description = description;
        this.shopId = shopId;
        this.services = services;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

}
