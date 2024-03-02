package com.samangkar.Samangkar.model;

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

    @ManyToOne
    private ServiceModel service;

    private boolean paid;
    private double total;
    private int quantity;

    public UserCard() {}

    public UserCard(UserEntity user, ServiceModel service, double total, int quantity) {
        this.user = user;
        // this.shop = shop;
        this.service = service;
        this.total = total;
        this.quantity = quantity;
    }

}
