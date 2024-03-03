package com.samangkar.Samangkar.model;

import java.util.Set;

import jakarta.persistence.*;

@Entity
public class ServiceModel {

    private String name;
    private String description;
    private double unitPrice;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToMany(mappedBy = "services")
    private Set<PackageModel> packages;

    public ServiceModel() {}

    public ServiceModel(String name, String description, Shop shop, double unitPrice) {
        this.name = name;
        this.description = description;
        this.shop = shop;
        this.unitPrice = unitPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

}
