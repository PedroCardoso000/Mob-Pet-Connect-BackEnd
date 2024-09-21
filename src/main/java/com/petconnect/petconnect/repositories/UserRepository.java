package com.petconnect.petconnect.repositories;

import com.petconnect.petconnect.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
