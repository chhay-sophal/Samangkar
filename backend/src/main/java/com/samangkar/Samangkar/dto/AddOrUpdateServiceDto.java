package com.samangkar.Samangkar.dto;

import lombok.Data;

@Data
public class AddOrUpdateServiceDto {
    
    private Long id;
    private String name;
    private String description;
    private double unitPrice;
    private Long shopId;
    private boolean isTrending;

    public AddOrUpdateServiceDto(Long id, String name, String description, double unitPrice, Long shopId,
            boolean isTrending) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.shopId = shopId;
        this.isTrending = isTrending;
    }

}
