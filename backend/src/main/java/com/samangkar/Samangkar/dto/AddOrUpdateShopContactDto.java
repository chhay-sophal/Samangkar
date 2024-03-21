package com.samangkar.Samangkar.dto;

import lombok.Data;

@Data
public class AddOrUpdateShopContactDto {
    
    private Long contactTypeId;
    private String url;
    private Long shopId;
    
    public AddOrUpdateShopContactDto(Long contactTypeId, String url, Long shopId) {
        this.contactTypeId = contactTypeId;
        this.url = url;
        this.shopId = shopId;
    }

}
