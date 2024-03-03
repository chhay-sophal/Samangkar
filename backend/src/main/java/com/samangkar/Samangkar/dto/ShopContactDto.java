package com.samangkar.Samangkar.dto;

import lombok.Data;

@Data
public class ShopContactDto {
    
    private Long contactId;
    private String contactType;
    private String url;
    private String shopName;

    public ShopContactDto(Long contactId, String contactType, String url, String shopName) {
        this.contactId = contactId;
        this.contactType = contactType;
        this.url = url;
        this.shopName = shopName;
    }

}
