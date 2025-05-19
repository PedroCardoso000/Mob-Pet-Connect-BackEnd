package com.petconnect.petconnect.controllers;


import com.petconnect.petconnect.Entities.User;
import com.petconnect.petconnect.Exceptions.PasswordMatchException;
import com.petconnect.petconnect.Exceptions.UserNotFoundException;
import com.petconnect.petconnect.Exceptions.UserUnauthorizedException;
import com.petconnect.petconnect.dtos.CreateUserRequest;
import com.petconnect.petconnect.dtos.UserDto;
import com.petconnect.petconnect.repositories.UserRepository;
import com.petconnect.petconnect.services.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<User> deleteUser(@PathVariable Long id) throws UserNotFoundException, UserUnauthorizedException {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User deletedUser = userService.deleteUser(id, loggedUser);
        return ResponseEntity.ok(deletedUser);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUserBody) throws UserNotFoundException, UserUnauthorizedException {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User updatedUser = userService.updateUser(id, updatedUserBody, loggedUser);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/bytoken")
    public ResponseEntity<UserDto> getUserById() throws UserUnauthorizedException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof User userDetails) {
            Optional<User> userOptional = userRepository.findById(userDetails.getId());
            return userOptional.map(user -> ResponseEntity.ok(UserDto.fromEntity(user)))
                    .orElseThrow(() -> new UserUnauthorizedException("Usuário não encontrado."));
        } else {
            throw new UserUnauthorizedException("Usuário não autenticado.");
        }
    }



}
