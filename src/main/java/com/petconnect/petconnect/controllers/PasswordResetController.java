package com.petconnect.petconnect.controllers;

import com.petconnect.petconnect.Entities.User;
import com.petconnect.petconnect.services.EmailService;
import com.petconnect.petconnect.services.PasswordResetTokenService;
import com.petconnect.petconnect.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/reset")
public class PasswordResetController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordResetTokenService passwordResetTokenService;

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<?> sendPasswordResetEmail(@RequestBody String email) {
        System.out.println("\n entrou no controller pelo menos");
        User user = userService.findByEmail(email);
        System.out.println("user" + user);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        String token = UUID.randomUUID().toString();
        passwordResetTokenService.createPasswordResetTokenForUser(user, token);

        // Send email
        String resetUrl = "http://http://localhost:5173/reset-password?token=" + token;
        emailService.sendPasswordResetEmail(user.getEmail(), resetUrl);

        return ResponseEntity.ok("Reset password email sent");
    }
}
