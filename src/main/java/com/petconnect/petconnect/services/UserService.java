package com.petconnect.petconnect.services;

import com.petconnect.petconnect.Entities.User;
import com.petconnect.petconnect.Exceptions.PasswordMatchException;
import com.petconnect.petconnect.Exceptions.UserNotFoundException;
import com.petconnect.petconnect.Exceptions.UserUnauthorizedException;
import com.petconnect.petconnect.dtos.CreateUserRequest;
import com.petconnect.petconnect.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public User deleteUser(Long userId, User loggedUser) throws UserNotFoundException {
        checkPathIdIsFromLoggedUser(loggedUser, userId);

        User userEntity = userRepository.findById(userId).orElse(null);
        if (userEntity == null) {
            throw new UserNotFoundException("User not found");
        }

        userRepository.deleteById(userId);
        return userEntity;
    }

    public  User updateUser(Long userId, User updatedUserBody, User loggedUser) throws PasswordMatchException, UserNotFoundException {
        checkPathIdIsFromLoggedUser(loggedUser, userId);

        User userEntity = userRepository.findById(userId).orElse(null);
        if (userEntity == null) {
            throw new UserNotFoundException("User not found");
        }

        userEntity.setName(updatedUserBody.getName());
        userEntity.setEmail(updatedUserBody.getEmail());
        userEntity.setPhone(updatedUserBody.getPhone());
        userEntity.setCpf(updatedUserBody.getCpf());
        userRepository.save(userEntity);

        return userEntity;
    }

    private void checkPathIdIsFromLoggedUser(User loggedUser, Long userId) {
        if(!loggedUser.getId().equals(userId)) {
            throw new UserUnauthorizedException("User can only update himself.");
        }
    }

    public User findByEmail(String email) {
        User findUser = userRepository.findByEmail(email);
        if (findUser == null) {
            throw new UserNotFoundException();
        } else {
            return findUser;
        }
    }

    public User findById(Long id) {
        Optional<User> findUser = userRepository.findById(id);
        if (findUser.isEmpty()) {
            throw new UserNotFoundException();
        } else {
            return findUser.orElse(null);
        }
    }
}
