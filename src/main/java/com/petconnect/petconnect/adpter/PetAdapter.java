package com.petconnect.petconnect.adpter;

import com.petconnect.petconnect.Entities.Pet;
import com.petconnect.petconnect.Entities.User;
import com.petconnect.petconnect.dtos.PetDtoObejct;
import com.petconnect.petconnect.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PetAdapter {


    public PetDtoObejct convertPetOb(Pet pet) {
        return new PetDtoObejct(
                pet.getId(),
                pet.getName(),
                pet.getSpecie(),
                pet.getRace(),
                pet.getGender(),
                pet.getImage(),
                pet.getVaccines(),
                pet.getUser().getId()
        );
    }

    public List<PetDtoObejct> convertPetOb(List<Pet> pets) {
        return pets.stream()
                .map(this::convertPetOb)
                .collect(Collectors.toList());
    }



}


