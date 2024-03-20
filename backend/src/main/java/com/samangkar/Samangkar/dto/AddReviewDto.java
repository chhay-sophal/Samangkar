package com.samangkar.Samangkar.dto;

import lombok.Data;

@Data
public class AddReviewDto {

    private Long userId;
    private Long shopId;
    private String title;
    private String description;
    private int stars;

    public AddReviewDto(Long userId, Long shopId, String title, String description, int stars) {
        this.userId = userId;
        this.shopId = shopId;
        this.title = title;
        this.description = description;
        this.stars = stars;
    }
    
}
