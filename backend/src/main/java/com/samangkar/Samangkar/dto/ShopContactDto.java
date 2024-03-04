package com.samangkar.Samangkar.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ShopContactDto {
    
    private Long contactId;
    private String contactType;
    private String url;
    private ShopDto shop;
    private Date createdAt;
    private Date updatedAt;
    
    public ShopContactDto(Long contactId, String contactType, String url, ShopDto shop, Date createdAt,
            Date updatedAt) {
        this.contactId = contactId;
        this.contactType = contactType;
        this.url = url;
        this.shop = shop;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
