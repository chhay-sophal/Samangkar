package com.samangkar.Samangkar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class ShopRequest {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Shop shop;
    
    private String purpose;
    private String description;

    public ShopRequest(Shop shop, String title, String description) {
        this.shop = shop;
        this.purpose = title;
        this.description = description;
    }
}
