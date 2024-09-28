package com.petconnect.petconnect.services;

import com.petconnect.petconnect.Entities.User;
import com.petconnect.petconnect.Exceptions.PasswordMatchException;
import com.petconnect.petconnect.dtos.CreateUserRequest;
import com.petconnect.petconnect.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(CreateUserRequest createUserRequest) throws PasswordMatchException {
        String passwordHash = passwordEncoder.encode(createUserRequest.password());

        User userEntity = new User(createUserRequest, passwordHash);
        return userRepository.save(userEntity);
    }

    public User deleteUser(Long userId, User loggedUser) {

        if(!loggedUser.getId().equals(userId)) {
            throw new AccessDeniedException("User can only delete himself.");
        }

        User userEntity = userRepository.findById(userId).orElse(null);
        if (userEntity == null) {
            throw new EntityNotFoundException("User not found");
        }

        userRepository.deleteById(userId);

        return userEntity;
    }
}
