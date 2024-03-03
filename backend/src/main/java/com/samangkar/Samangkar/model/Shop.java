package com.samangkar.Samangkar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private String shopImageUrl;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEntity owner;

    private boolean activated;
    private boolean trending;

    @OneToMany(mappedBy = "shop")
    private List<PackageModel> packageModels;

    @OneToMany(mappedBy = "shop")
    private List<ServiceModel> serviceModels;

    @OneToMany(mappedBy = "shop")
    private List<ShopContact> shopContacts;

    public Shop() {}
    public Shop(String name, String description, String shopImageUrl, UserEntity owner) {
        this.name = name;
        this.description = description;
        this.shopImageUrl = shopImageUrl;
        this.owner = owner;
    }

}
