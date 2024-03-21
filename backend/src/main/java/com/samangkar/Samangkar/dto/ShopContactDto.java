package com.samangkar.Samangkar.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ShopContactDto {
    
    private Long id;
    private Long contactTypeId;
    private String contactType;
    private String url;
    private Long shopId;
    private String shopName;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
    
    public ShopContactDto(Long id, Long contactTypeId, String contactType, String url, Long shopId,
            String shopName, Date createdAt, Date updatedAt, Date deletedAt) {
        this.id = id;
        this.contactTypeId = contactTypeId;
        this.contactType = contactType;
        this.url = url;
        this.shopId = shopId;
        this.shopName = shopName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }
    
}
