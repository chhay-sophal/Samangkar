package com.samangkar.Samangkar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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

    public Shop() {}
    public Shop(String name, String description, String shopImageUrl, UserEntity owner) {
        this.name = name;
        this.description = description;
        this.shopImageUrl = shopImageUrl;
        this.owner = owner;
    }

}
