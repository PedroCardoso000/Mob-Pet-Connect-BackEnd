package com.petconnect.petconnect.dtos;

import com.petconnect.petconnect.Entities.Pet;

public record PetDto(
        Long id,
        String name,
        String specie,
        String race
) {
    public static PetDto fromEntity(Pet pet) {
        return new PetDto(
                pet.getId(),
                pet.getName(),
                pet.getSpecie(),
                pet.getRace()
        );
    }
}

