package com.petconnect.petconnect.dtos;

import com.petconnect.petconnect.Exceptions.PasswordMatchException;

public record CreateUserRequest(
        String name,
        String email,
        String cpf,
        String phone,
        String password,
        String confirmPassword
) {
    public CreateUserRequest {
        boolean passwordsDoesntMatch = !password.equals(confirmPassword);
        if (passwordsDoesntMatch)
            throw new PasswordMatchException("PasswordMatchException: Passwords does not match");
    }
}
