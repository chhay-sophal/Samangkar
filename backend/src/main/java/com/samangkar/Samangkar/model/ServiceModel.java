package com.samangkar.Samangkar.model;

import java.util.Date;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.*;

@Entity
public class ServiceModel {

    private String name;
    private String description;
    private double unitPrice;
    private String image;
    private boolean isTrending;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToMany(mappedBy = "services")
    private Set<PackageModel> packages;

    @OneToMany(mappedBy = "service", cascade = CascadeType.REMOVE)
    private Set<UserCard> userCards;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "deleted_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

    @PrePersist
    public void prePersist() {
        createdAt = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = new Date();
    }

    public ServiceModel() {}

    public ServiceModel(String name, String description, Shop shop, double unitPrice) {
        this.name = name;
        this.description = description;
        this.shop = shop;
        this.unitPrice = unitPrice;
        this.isTrending = false;
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

    public Set<PackageModel> getPackages() {
        return packages;
    }

    public void setPackages(Set<PackageModel> packages) {
        this.packages = packages;
    }

    public Set<UserCard> getUserCards() {
        return userCards;
    }

    public void setUserCards(Set<UserCard> userCards) {
        this.userCards = userCards;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isTrending() {
        return isTrending;
    }

    public void setTrending(boolean isTrending) {
        this.isTrending = isTrending;
    }

}
