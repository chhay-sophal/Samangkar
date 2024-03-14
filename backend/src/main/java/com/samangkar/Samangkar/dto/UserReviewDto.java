package com.samangkar.Samangkar.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserReviewDto {
    private Long id;
    private String title;
    private UserDto user;
    private Long shop_id;
    private String description;
    private int stars;
    private Date createdAt;
    private Date deletedAt;
    private Date updatedAt;

    public UserReviewDto(){};

}
