package com.petconnect.petconnect.dtos;

public record CreateUserRequest(
        String name,
        String email,
        String cpf,
        String phone,
        String password,
        String confirmPassword
) {}
