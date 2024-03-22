package com.samangkar.Samangkar.dto;

import lombok.Data;

@Data
public class RequestDto {
    private Long id;
    private Long shopId;
    private String shopName;
    private String title;
    private String description;

    public RequestDto(Long id, Long shopId, String shopName, String title, String description) {
        this.id = id;
        this.shopId = shopId;
        this.shopName = shopName;
        this.title = title;
        this.description = description;
    }

}
