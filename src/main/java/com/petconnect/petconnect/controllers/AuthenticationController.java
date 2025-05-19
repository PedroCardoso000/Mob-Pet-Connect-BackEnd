package com.petconnect.petconnect.controllers;

import com.petconnect.petconnect.Entities.User;
import com.petconnect.petconnect.dtos.CreateUserRequest;
import com.petconnect.petconnect.dtos.LoginDTO;
import com.petconnect.petconnect.dtos.LoginResDTO;
import com.petconnect.petconnect.services.TokenService;
import com.petconnect.petconnect.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResDTO> login(@RequestBody @Validated LoginDTO loginDTO) throws UsernameNotFoundException {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.password());
        var authentication = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new LoginResDTO(token));
    }

}
