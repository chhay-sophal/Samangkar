package com.samangkar.Samangkar.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ShopDto {
    private String name;
    private String shopImageUrl;
    private String shopOwner;

    public ShopDto(String name, String shopImageUrl, String shopOwner) {
        this.name = name;
        this.shopImageUrl = shopImageUrl;
        this.shopOwner = shopOwner;
    }
}
