package com.samangkar.Samangkar.model;

import jakarta.persistence.*;

@Entity
public class UserReview {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private Shop shop;

    private String title;
    private String description;
    private int stars;

    public UserReview() {}

    public UserReview(UserEntity user, Shop shop, int stars, String title, String description) {
        this.user = user;
        this.shop = shop;
        this.stars = stars;
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
