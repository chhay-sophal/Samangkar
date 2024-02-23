package com.samangkar.Samangkar.model;

import jakarta.persistence.*;

@Entity
public class PackageModel {

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

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    public PackageModel() {}
    public PackageModel(String name, String description, Shop shop) {
        this.name = name;
        this.description = description;
        this.shop = shop;
    }
}
