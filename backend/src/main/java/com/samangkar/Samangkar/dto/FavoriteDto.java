package com.samangkar.Samangkar.dto;

import java.util.Date;

import lombok.Data;

@Data
public class FavoriteDto {
    private Long id;
    private UserDto user;
    private ShopDto shop;
    private Date createdAt;
    private Date updatedAt;
    
    public FavoriteDto(Long id, UserDto user, ShopDto shop, Date createdAt, Date updatedAt) {
        this.id = id;
        this.user = user;
        this.shop = shop;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
