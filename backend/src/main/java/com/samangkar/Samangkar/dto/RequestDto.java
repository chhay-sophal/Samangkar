package com.samangkar.Samangkar.dto;

import lombok.Data;

@Data
public class RequestDto {
    private Long id;
    private ShopDto shop;
    private String title;
    private String description;
    
    public RequestDto(Long id, ShopDto shop, String title, String description) {
        this.id = id;
        this.shop = shop;
        this.title = title;
        this.description = description;
    }
}
