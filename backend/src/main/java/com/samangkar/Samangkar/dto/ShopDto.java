package com.samangkar.Samangkar.dto;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ShopDto {
    private Long shopId;
    private String name;
    private String shopImageUrl;
    private UserDto shopOwner;
    private Date createdAt;
    private Date updatedAt;

    public ShopDto(Long shopId, String name, String shopImageUrl, UserDto shopOwner, Date createdAt, Date updatedAt) {
        this.shopId = shopId;
        this.name = name;
        this.shopImageUrl = shopImageUrl;
        this.shopOwner = shopOwner;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
