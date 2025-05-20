package com.petconnect.petconnect.dtos;
import com.petconnect.petconnect.Entities.User;
import com.petconnect.petconnect.Exceptions.PasswordMatchException;

import java.util.Date;

public record CreatePetRequest(
        String name,
        String gender,
        Date birthDate,
        String specie,
        String race,
        User user
) {}
