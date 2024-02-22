package com.samangkar.Samangkar.model;

import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class UserEntity {

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public ProfileEntity getProfile() {
        return profileEntity;
    }

    public void setProfile(ProfileEntity profileEntity) {
        this.profileEntity = profileEntity;
    }

    public UserTypeEntity getUserType() {
        return userTypeEntity;
    }

    public void setUserType(UserTypeEntity userTypeEntity) {
        this.userTypeEntity = userTypeEntity;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getActivationToken() {
        return activationToken;
    }

    public void setActivationToken(String activationToken) {
        this.activationToken = activationToken;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    private String email;
    @Column(nullable = false)
    private String password;
    @OneToOne
    private ProfileEntity profileEntity;
    @ManyToOne
    private UserTypeEntity userTypeEntity;
    private boolean activated;
    @Column(length = 64)
    private String activationToken;

    public boolean matchPassword(String rawPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, this.password);
    }

}
