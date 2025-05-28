package com.petconnect.petconnect.dtos;

import java.util.List;

public record PetDtoObejct (
        Long id,
        String name,
        String specie,
        String race,
        com.petconnect.petconnect.enums.PetGender gender,
        String image,
        List<String> vaccines,
        Long userId) {
}
