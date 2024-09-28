package com.petconnect.petconnect.controllers;


import com.petconnect.petconnect.Entities.User;
import com.petconnect.petconnect.Exceptions.PasswordMatchException;
import com.petconnect.petconnect.dtos.CreateUserRequest;
import com.petconnect.petconnect.repositories.UserRepository;
import com.petconnect.petconnect.services.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@Validated @RequestBody CreateUserRequest createUserRequest) throws PasswordMatchException {
        User createdUser = userService.createUser(createUserRequest);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping
    public ResponseEntity<String> getUsers() {
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User deletedUser = userService.deleteUser(id, loggedUser);
        return ResponseEntity.ok(deletedUser);
    }

}
