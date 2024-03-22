package com.samangkar.Samangkar.dto;

import java.util.Date;

import lombok.Data;

@Data
public class RequestDto {
    private Long id;
    private Long shopId;
    private String shopName;
    private String purpose;
    private String description;
    private Date createdAt;

    public RequestDto(Long id, Long shopId, String shopName, String purpose, String description, Date createdAt) {
        this.id = id;
        this.shopId = shopId;
        this.shopName = shopName;
        this.purpose = purpose;
        this.description = description;
        this.createdAt = createdAt;
    }

}
