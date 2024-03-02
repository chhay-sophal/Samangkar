package com.samangkar.Samangkar.dto;

import lombok.Data;

@Data
public class FavoriteDto {
    private Long userId;
    private Long shopId;
    public FavoriteDto(Long userId, Long shopId) {
        this.userId = userId;
        this.shopId = shopId;
    }
}
