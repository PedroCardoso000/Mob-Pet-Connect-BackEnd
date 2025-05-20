package com.petconnect.petconnect.enums;

public enum PetGender {
    MALE,
    FEMALE;

    public static PetGender fromString(String gender) {
        try {
            return PetGender.valueOf(gender.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid gender: " + gender);
        }
    }
}


