package com.samangkar.Samangkar.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Entity
public class User {

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
    @JoinColumn(name = "user_type_id") // Clarify the join column.
    private UserType userType;

    private String profileUrl;

    private boolean active = true;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserFavorite> favoriteShops;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserReview> reviewShops;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserCard> cards;

    // Default constructor is required by JPA.
    public User() {}

    // Custom constructor to handle new user creation.
    public User(String username, String email, String plainPassword, UserType userType) {
        this.username = username;
        this.email = email;
        setPassword(plainPassword); // Use the setter to hash the password.
        this.userType = userType;
    }

    public long getUser_id() {
        return id;
    }

    public void setUser_id(Long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    // Set password and automatically hash it.
    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
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

}
