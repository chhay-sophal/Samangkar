package com.samangkar.Samangkar.model;

import jakarta.persistence.*;

@Entity
public class ServiceEntity {

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

    public ShopEntity getProvider() {
        return provider;
    }

    public void setProvider(ShopEntity provider) {
        this.provider = provider;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL)
    private ShopEntity provider;
}
