package com.samangkar.Samangkar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String description;
    private String shopImageUrl;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEntity owner;

    private boolean activated;
    private boolean trending;

    private Date last_modified_date;

    @OneToMany(mappedBy = "shop")
    private List<PackageModel> packageModels;

    @OneToMany(mappedBy = "shop")
    private List<ServiceModel> serviceModels;

    @OneToMany(mappedBy = "shop")
    private List<ShopContact> shopContacts;

    @ManyToOne
    @JoinColumn(name = "create_by")
    private UserEntity create_by;



    public Shop() {}
    public Shop(String name, String description, String shopImageUrl, UserEntity owner, Date last_modified_date) {
        this.name = name;
        this.description = description;
        this.shopImageUrl = shopImageUrl;
        this.owner = owner;
        this.last_modified_date = last_modified_date;
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

    public List<PackageModel> getPackageModels() {
        return packageModels;
    }

    public void setPackageModels(List<PackageModel> packageModels) {
        this.packageModels = packageModels;
    }

    public List<ServiceModel> getServiceModels() {
        return serviceModels;
    }

    public void setServiceModels(List<ServiceModel> serviceModels) {
        this.serviceModels = serviceModels;
    }

    public List<ShopContact> getShopContacts() {
        return shopContacts;
    }

    public void setShopContacts(List<ShopContact> shopContacts) {
        this.shopContacts = shopContacts;
    }

    public UserEntity getCreate_by() {
        return create_by;
    }

    public void setCreate_by(UserEntity create_by) {
        this.create_by = create_by;
    }

    public Date getLast_modified_date() {
        return last_modified_date;
    }

    public void setLast_modified_date(Date last_modified_date) {
        this.last_modified_date = last_modified_date;
    }
}
