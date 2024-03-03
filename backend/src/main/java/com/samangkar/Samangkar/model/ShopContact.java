package com.samangkar.Samangkar.model;

import jakarta.persistence.*;

@Entity
public class ShopContact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contact_type_id")
    private ContactType contactType;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public ShopContact() {}

    public ShopContact(ContactType contactType, String url, Shop shop) {
        this.contactType = contactType;
        this.url = url;
        this.shop = shop;
    }
}
