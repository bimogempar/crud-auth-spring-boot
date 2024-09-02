package com.crud_spring_boot.crud_spring_boot.domain.repository;

import com.crud_spring_boot.crud_spring_boot.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
}