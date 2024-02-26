package com.samangkar.Samangkar.model;

import jakarta.persistence.*;

@Entity
public class UserFavorite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private Shop shop;

    public UserFavorite() {}

    public UserFavorite(UserEntity user, Shop shop) {
        this.user = user;
        this.shop = shop;
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
}
