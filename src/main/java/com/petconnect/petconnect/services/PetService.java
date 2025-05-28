package com.petconnect.petconnect.services;

import com.petconnect.petconnect.Entities.Pet;
import com.petconnect.petconnect.Entities.User;
import com.petconnect.petconnect.Exceptions.PetNotFoundException;
import com.petconnect.petconnect.Exceptions.UserUnauthorizedException;
import com.petconnect.petconnect.adpter.PetAdapter;
import com.petconnect.petconnect.dtos.CreatePetRequest;
import com.petconnect.petconnect.enums.PetGender;
import com.petconnect.petconnect.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;


    //Problema
    public Pet createPet(CreatePetRequest request) {
            PetGender gender = PetGender.fromString(request.gender());
        System.out.println("Resultado " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            Pet pet = new Pet(request, gender, loggedUser);
            return petRepository.save(pet);
    }

    public Pet findPetById(Long petId) {
        Optional<Pet> pet = petRepository.findById(petId);

        if(pet.isEmpty()) throw new PetNotFoundException();
        return pet.get();
    }

    public  List<Pet> getUserPets(Long userId) {
        List<Pet> userPets = petRepository.findByUserId(userId);
        return userPets;
    }

    public List<Pet> listPets() {
        return petRepository.findAll();
    }

    public Pet updatePet (Long petId, Pet updatedPetBody) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<Pet> petOption = petRepository.findById(petId);

        if (petOption.isEmpty()) {
            throw new PetNotFoundException();
        }

        Pet petEntity = petOption.get();

        boolean loggedUserIsPetUser = loggedUser.getId().equals(petEntity.getUser().getId());
        if (!loggedUserIsPetUser) {
            throw new UserUnauthorizedException("This pet does not belong to logged user.");
        }

        petEntity.setName(updatedPetBody.getName());
        petEntity.setGender(updatedPetBody.getGender());
        petEntity.setBirthDate(updatedPetBody.getBirthDate());
        petEntity.setRace(updatedPetBody.getRace());
        petEntity.setSpecie(updatedPetBody.getSpecie());
        petEntity.setVaccines(updatedPetBody.getVaccines());
        petRepository.save(petEntity);

        return petEntity;
    }

    public void deletePet(Long petId) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Pet petEntity = petRepository
                .findById(petId)
                .orElseThrow(PetNotFoundException::new);

        boolean loggedUserIsPetUser = loggedUser.getId().equals(petEntity.getUser().getId());
        if (!loggedUserIsPetUser) {
            throw new UserUnauthorizedException("This pet does not belong to logged user.");
        }

        petRepository.deleteById(petId);
    }

    public void uploadPetImage(MultipartFile file, Long petId) throws IOException {
        String fileName = UUID.randomUUID().toString() + ".jpg";
        Path filePath = Paths.get("images/pet", fileName);
        try {
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());
        } catch (IOException e) {
            throw new IOException(e);
        }

        Pet pet = petRepository
                .findById(petId)
                .orElseThrow(PetNotFoundException::new);

        pet.setImage(fileName);
        petRepository.save(pet);
    }
}