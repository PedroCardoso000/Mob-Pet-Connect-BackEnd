package com.petconnect.petconnect.controllers;


import com.petconnect.petconnect.Entities.User;
import com.petconnect.petconnect.dtos.CreateUserRequest;
import com.petconnect.petconnect.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest createUserRequest) {
        User userEntity = new User(createUserRequest);
        User createdUser = userRepository.save(userEntity);

        return ResponseEntity.ok(createdUser);
    }

}
