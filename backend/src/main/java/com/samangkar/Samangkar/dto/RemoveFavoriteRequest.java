package com.samangkar.Samangkar.dto;

import lombok.Data;

@Data
public class RemoveFavoriteRequest {
    private Long userId;
    private Long shopId;
    public RemoveFavoriteRequest(Long userId, Long shopId) {
        this.userId = userId;
        this.shopId = shopId;
    }
}
