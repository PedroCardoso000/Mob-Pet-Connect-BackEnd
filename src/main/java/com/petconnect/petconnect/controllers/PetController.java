package com.petconnect.petconnect.controllers;

import com.petconnect.petconnect.Entities.Pet;
import com.petconnect.petconnect.dtos.CreatePetRequest;
import com.petconnect.petconnect.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

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
    public  ResponseEntity<List<Pet>> listPets() {
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
    ResponseEntity<String> deletePet(@PathVariable Long id) {
        petService.deletePet(id);
        return ResponseEntity.ok("Pet successfully deleted");
    }

    @PostMapping("/upload-image/{id}")
    ResponseEntity<String> uploadPetImage(@RequestParam("image") MultipartFile image, @PathVariable Long id) {
        try {
            petService.uploadPetImage(image, id);
            return ResponseEntity.ok("Pet image created");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed.");
        }
    }

    @GetMapping("/image/{petImage}")
    ResponseEntity<Resource> getPetImage(@PathVariable String petImage) {
        try {
            Path filePath = Paths.get("images/pet", petImage);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                String contentType = Files.probeContentType(filePath);

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(Optional.ofNullable(contentType).orElse("application/octet-stream")))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
