package com.samangkar.Samangkar.model;

import jakarta.persistence.*;

@Entity
public class ShopContactEntity {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ContactTypeEntity getContactType() {
        return contactType;
    }

    public void setContactType(ContactTypeEntity contactType) {
        this.contactType = contactType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ShopEntity getShop() {
        return shop;
    }

    public void setShop(ShopEntity shop) {
        this.shop = shop;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private ContactTypeEntity contactType;
    private String url;
    @ManyToOne
    private ShopEntity shop;

}
