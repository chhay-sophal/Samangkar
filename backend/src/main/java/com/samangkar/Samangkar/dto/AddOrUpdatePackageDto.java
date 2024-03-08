package com.samangkar.Samangkar.dto;

import java.util.Set;

import lombok.Data;

@Data
public class AddOrUpdatePackageDto {
    
    private String packageName;
    private String description;
    private Long shopId;
    private Set<Long> serviceIds;
    
    public AddOrUpdatePackageDto(String packageName, String description, Long shopId, Set<Long> serviceIds) {
        this.packageName = packageName;
        this.description = description;
        this.shopId = shopId;
        this.serviceIds = serviceIds;
    }

}
