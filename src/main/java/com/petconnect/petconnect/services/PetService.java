package com.petconnect.petconnect.services;

import com.petconnect.petconnect.Entities.Pet;
import com.petconnect.petconnect.Entities.User;
import com.petconnect.petconnect.dtos.CreatePetRequest;
import com.petconnect.petconnect.enums.PetGender;
import com.petconnect.petconnect.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    public Pet createPet(CreatePetRequest request) {
            PetGender gender = PetGender.fromString(request.gender());
            User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            Pet pet = new Pet(request, gender, loggedUser);
            return petRepository.save(pet);
    }

}