package com.samangkar.Samangkar.dto;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ShopDto {
    private Long id;
    private String name;
    private String imageUrl;
    private Long ownerId;
    private String ownerUsername;
    private String description;
    private Boolean isTrending;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public ShopDto(Long id, String name, String imageUrl, Long ownerId, String ownerUsername, String description,
            Boolean isTrending, Date createdAt, Date updatedAt, Date deletedAt) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.ownerId = ownerId;
        this.ownerUsername = ownerUsername;
        this.description = description;
        this.isTrending = isTrending;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

}
