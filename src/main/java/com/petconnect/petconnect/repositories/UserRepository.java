package com.petconnect.petconnect.repositories;

import com.petconnect.petconnect.Entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    @EntityGraph(attributePaths = "pets")
    Optional<User> findById(Long id);
}
