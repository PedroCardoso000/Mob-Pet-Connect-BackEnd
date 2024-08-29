package com.petconnect.petconnect.controllers;


import com.petconnect.petconnect.domain.Pessoa;
import com.petconnect.petconnect.dto.DtoComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

// LOCALHOST:8080/AUTH

@RestController
@RequestMapping("/auth")
public class BreenoControllers {
    // Injeção de dependencia
    @Autowired
    private Pessoa pessoa;

    @GetMapping()
    public String hello(){  
        return "Hello";
    }


    @PostMapping("/postar")
    public String receberNome(@RequestBody String nome){
        return "Meu nome é " + nome;
    };

    @PutMapping("/{id}")
    public String receberID(@PathVariable Integer id, @RequestBody DtoComponent exemplo){
        return "Meu nome é " + id;
    }




}
