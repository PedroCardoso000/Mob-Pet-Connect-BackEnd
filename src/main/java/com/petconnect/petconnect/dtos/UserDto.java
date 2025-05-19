package com.petconnect.petconnect.dtos;

import com.petconnect.petconnect.Entities.User;

import java.util.List;

public record UserDto(
        Long id,
        String name,
        String email,
        String passwordHash,
        String phone,
        String cpf,
        List<PetDto> pets
) {
    public static UserDto fromEntity(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword_hash(),
                user.getPhone(),
                user.getCpf(),
                user.getPets().stream().map(PetDto::fromEntity).toList()
        );
    }
}

