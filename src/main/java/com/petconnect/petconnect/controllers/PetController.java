package com.petconnect.petconnect.controllers;

import com.petconnect.petconnect.Entities.Pet;
import com.petconnect.petconnect.Entities.User;
import com.petconnect.petconnect.Exceptions.UserNotFoundException;
import com.petconnect.petconnect.Exceptions.UserUnauthorizedException;
import com.petconnect.petconnect.dtos.CreatePetRequest;
import com.petconnect.petconnect.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    @PostMapping
    public ResponseEntity<Pet> createPet(@Validated @RequestBody CreatePetRequest request) {
        Pet createdPet = petService.createPet(request);
        return ResponseEntity.ok(createdPet);
    }

    @GetMapping
    public  ResponseEntity<List<Pet>> getPetById() {
        List<Pet> pets = petService.listPets();
        return  ResponseEntity.ok(pets);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Pet> getPetById(@PathVariable Long id) {
        Pet pet = petService.findPetById(id);
        return  ResponseEntity.ok(pet);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Pet>> getUserPets(@PathVariable Long id) {
        List<Pet> userPets = petService.getUserPets(id);
        return  ResponseEntity.ok(userPets);
    }

    @PatchMapping("/{id}")
    ResponseEntity<Pet> updatePet(@PathVariable Long id, @RequestBody Pet updatedPetBody) {
        Pet updatedPet = petService.updatePet(id, updatedPetBody);
        return ResponseEntity.ok(updatedPet);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deletePet(@PathVariable Long id, @RequestBody Pet updatedPetBody) {
        petService.deletePet(id);
        return ResponseEntity.ok("Pet successfully deleted");
    }
}
