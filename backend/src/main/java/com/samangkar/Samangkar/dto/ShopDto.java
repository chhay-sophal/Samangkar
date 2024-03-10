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
    private UserDto owner;
    private String description;
    private Boolean isTrending;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public ShopDto(Long id, String name, String imageUrl, UserDto owner, String description, Boolean isTreading, Date createdAt, Date updatedAt, Date deletedAt) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.owner = owner;
        this.description = description;
        this.isTrending = isTreading;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

}
