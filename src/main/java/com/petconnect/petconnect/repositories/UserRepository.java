package com.petconnect.petconnect.repositories;

import com.petconnect.petconnect.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
