package com.samangkar.Samangkar.model;

import jakarta.persistence.*;

@Entity
public class Shop {

    public Shop() {}
    public Shop(String name, String description, String shopImageUrl, User owner) {
        this.name = name;
        this.description = description;
        this.shopImageUrl = shopImageUrl;
        this.owner = owner;
    }

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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String description;
    private String shopImageUrl;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    private boolean activated;
    private boolean trending;

}
