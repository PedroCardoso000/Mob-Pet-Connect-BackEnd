package com.petconnect.petconnect.controllers;

import com.petconnect.petconnect.Entities.Pet;
import com.petconnect.petconnect.dtos.CreatePetRequest;
import com.petconnect.petconnect.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    public ResponseEntity<Pet> createPet(@Validated @RequestBody CreatePetRequest request) {
        Pet createdPet = petService.createPet(request);
        return ResponseEntity.ok(createdPet);
    }
}
