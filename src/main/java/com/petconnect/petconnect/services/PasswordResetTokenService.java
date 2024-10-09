package com.petconnect.petconnect.services;

import com.petconnect.petconnect.Entities.PasswordResetToken;
import com.petconnect.petconnect.Entities.User;
import com.petconnect.petconnect.repositories.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PasswordResetTokenService {

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken passwordResetToken = new PasswordResetToken(token, user, LocalDateTime.now().plusHours(1)); // 1 hour expiry
        tokenRepository.save(passwordResetToken);
    }
}

