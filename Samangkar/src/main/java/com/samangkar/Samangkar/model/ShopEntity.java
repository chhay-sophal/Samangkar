package com.samangkar.Samangkar.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ShopEntity {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShopImageUrl() {
        return shopImageUrl;
    }

    public void setShopImageUrl(String shopImageUrl) {
        this.shopImageUrl = shopImageUrl;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public boolean isTrending() {
        return trending;
    }

    public void setTrending(boolean trending) {
        this.trending = trending;
    }

    public List<ShopContactEntity> getContacts() {
        return contacts;
    }

    public void setContacts(List<ShopContactEntity> contacts) {
        this.contacts = contacts;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    private String shopImageUrl;
    private UserEntity owner;
    private boolean activated;
    private boolean trending;
    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<ShopContactEntity> contacts;

}
