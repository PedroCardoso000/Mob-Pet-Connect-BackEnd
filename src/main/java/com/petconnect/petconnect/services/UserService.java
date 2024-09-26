package com.petconnect.petconnect.services;

import com.petconnect.petconnect.Entities.User;
import com.petconnect.petconnect.Exceptions.PasswordMatchException;
import com.petconnect.petconnect.dtos.CreateUserRequest;
import com.petconnect.petconnect.repositories.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(CreateUserRequest createUserRequest) throws PasswordMatchException {
        boolean passwordsDoesntMatch = !createUserRequest.password().equals(createUserRequest.confirmPassword());

        if (passwordsDoesntMatch) {
            throw new PasswordMatchException("PasswordMatchException: Passwords does not match");
        }

        User userEntity = new User(createUserRequest);
        return userRepository.save(userEntity);
    }
}
