package com.samangkar.Samangkar.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class UserReview {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private Shop shop;

    private String title;
    private String description;
    private int stars;

    private Boolean is_deleted;
    private Boolean is_updated;
    private Date create_date;
    private Date last_modified_date;

    public UserReview() {}

    public UserReview(UserEntity user, Shop shop, int stars, String title, String description,Boolean is_deleted, Boolean is_updated, Date create_date, Date last_modified_date) {
        this.user = user;
        this.shop = shop;
        this.stars = stars;
        this.title = title;
        this.description = description;
        this.is_deleted = is_deleted;
        this.is_updated = is_updated;
        this.create_date = create_date;
        this.last_modified_date = last_modified_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public Boolean getIs_updated() {
        return is_updated;
    }

    public void setIs_updated(Boolean is_updated) {
        this.is_updated = is_updated;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getLast_modified_date() {
        return last_modified_date;
    }

    public void setLast_modified_date(Date last_modified_date) {
        this.last_modified_date = last_modified_date;
    }
}
