package com.samangkar.Samangkar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllShopDto {
    private Long id;
    private String name;
    private String description;
    private String shopImageUrl;
    private boolean activated;
    private boolean trending;
    private Long owner_id;

    // Default constructor
    public AllShopDto() {
    }

    // Constructor with parameters
    public AllShopDto(Long id, String name, String description, String shopImageUrl, boolean activated, boolean trending, Long owner_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.shopImageUrl = shopImageUrl;
        this.activated = activated;
        this.trending = trending;
        this.owner_id = owner_id;

    }
}
