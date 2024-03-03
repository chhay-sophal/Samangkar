package com.samangkar.Samangkar.dto;

import java.util.List;

import lombok.Data;

@Data
public class PackageDto {
    
    private Long packageId;
    private String packageName;
    private String description;
    private Long shopId;
    private List<ServiceDto> services;
    private String createdAt;
    private String modifiedAt;

    public PackageDto(Long packageId, String packageName, String description, Long shopId, List<ServiceDto> services,
            String createdAt, String modifiedAt) {
        this.packageId = packageId;
        this.packageName = packageName;
        this.description = description;
        this.shopId = shopId;
        this.services = services;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

}
