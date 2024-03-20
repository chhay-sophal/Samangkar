package com.samangkar.Samangkar.dto;

import java.util.Date;

import lombok.Data;

@Data
public class FavoriteDto {
    private Long id;
    private Long userId;
    private String userUsername;
    private ShopDto shop;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
    
    public FavoriteDto(Long id, Long userId, String userUsername, ShopDto shop, Date createdAt,
            Date updatedAt, Date deletedAt) {
        this.id = id;
        this.userId = userId;
        this.userUsername = userUsername;
        this.shop = shop;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

}
