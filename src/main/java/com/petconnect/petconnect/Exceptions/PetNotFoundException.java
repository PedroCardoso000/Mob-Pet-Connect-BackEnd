package com.petconnect.petconnect.Exceptions;

public class PetNotFoundException extends RuntimeException {
  public PetNotFoundException() {
    super("No pet with this id was found.");
  }
}
