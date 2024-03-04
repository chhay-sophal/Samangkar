package com.samangkar.Samangkar.dto;

import lombok.Data;

@Data
public class AddFavoriteDto {
    
    private Long userId;
    private Long shopId;

    public AddFavoriteDto(Long userId, Long shopId) {
        this.userId = userId;
        this.shopId = shopId;
    }
    
}
