package com.samangkar.Samangkar.model;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class UserCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private UserEntity user;

    // @ManyToOne
    // private Shop shop;

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
    
    @ManyToOne
    private ServiceModel service;

    @ManyToOne
    private PackageModel pkg;

    private boolean paid;
    private double total;
    private int quantity;

    public UserCard() {}

    public UserCard(UserEntity user, ServiceModel service, PackageModel pkg, double total, int quantity) {
        this.user = user;
        // this.shop = shop;
        this.service = service;
        this.pkg = pkg;
        this.total = total;
        this.quantity = quantity;
    }

}
