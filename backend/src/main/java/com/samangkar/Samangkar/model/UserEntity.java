package com.samangkar.Samangkar.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for auto-increment columns.
    private Long id; // It's common to use wrapper classes for IDs.

    @NotBlank(message = "Username is mandatory")
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Length(min = 8, message = "Password should have at least 8 characters")
    private String password; // Store the hashed password directly.

    @ManyToOne
    @JoinColumn(name = "user_role_id") // Clarify the join column.
    private Role userRole;

    private String profileUrl;

    private boolean active = true;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserFavorite> favoriteShops;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserReview> reviewShops;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserCard> cards;

    // Default constructor is required by JPA.
    public UserEntity() {}

    // Custom constructor to handle new user creation.
    public UserEntity(String username, String email, String plainPassword, Role userRole) {
        this.username = username;
        this.email = email;
        setPassword(plainPassword); // Use the setter to hash the password.
        this.userRole = userRole;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<UserFavorite> getFavoriteShops() {
        return favoriteShops;
    }

    public void setFavoriteShops(List<UserFavorite> favoriteShops) {
        this.favoriteShops = favoriteShops;
    }

    public List<UserReview> getReviewShops() {
        return reviewShops;
    }

    public void setReviewShops(List<UserReview> reviewShops) {
        this.reviewShops = reviewShops;
    }

    public List<UserCard> getCards() {
        return cards;
    }

    public void setCards(List<UserCard> cards) {
        this.cards = cards;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
