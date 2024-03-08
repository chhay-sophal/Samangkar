package com.samangkar.Samangkar.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AddOrUpdateShopDto {
    private String shopName;
    private String shopImageUrl;
    private Long shopOwnerId;
    private String description;
    private Boolean isTrending;

    public AddOrUpdateShopDto(String shopName, String shopImageUrl, Long shopOwnerId, String description, Boolean isTrending) {
        this.shopName = shopName;
        this.shopImageUrl = shopImageUrl;
        this.shopOwnerId = shopOwnerId;
        this.description = description;
        this.isTrending = isTrending;
    }

}
