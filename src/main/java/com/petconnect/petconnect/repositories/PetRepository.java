package com.petconnect.petconnect.repositories;

import com.petconnect.petconnect.Entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {

}
