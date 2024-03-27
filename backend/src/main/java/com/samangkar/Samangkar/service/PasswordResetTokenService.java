package com.samangkar.Samangkar.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samangkar.Samangkar.model.PasswordResetToken;
import com.samangkar.Samangkar.model.UserEntity;
import com.samangkar.Samangkar.repository.PasswordResetTokenRepository;

@Service
public class PasswordResetTokenService {
    
    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    // Token validity duration (e.g., 1 hour)
    private static final long EXPIRATION_TIME = 60 * 60 * 1000; 

    public PasswordResetToken createToken(UserEntity user) {
        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUser(user);
        resetToken.setExpiryDate(new Date(System.currentTimeMillis() + EXPIRATION_TIME));
        
        return tokenRepository.save(resetToken);
    }

    public PasswordResetToken findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public void expireToken(PasswordResetToken token) {
        token.setExpiryDate(new Date()); // Set to current time to expire
        tokenRepository.save(token);
    }

    public boolean isExpired(PasswordResetToken token) {
        return token.getExpiryDate().before(new Date());
    }

}
