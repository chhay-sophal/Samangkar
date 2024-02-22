package com.samangkar.Samangkar.service;

import com.samangkar.Samangkar.model.UserEntity;

import org.apache.commons.lang3.RandomStringUtils;

public class UserService {

    public void generateActivationToken(UserEntity user) {
        String token = RandomStringUtils.randomAlphanumeric(64);
        user.setActivationToken(token);
    }

    public boolean activateUser(UserEntity user, String providedToken) {
        if (user.getActivationToken() != null && user.getActivationToken().equals(providedToken)) {
            user.setActivated(true);
            user.setActivationToken(null); // Optionally, clear the token after activation
            return true;
        }
        return false;
    }

}
