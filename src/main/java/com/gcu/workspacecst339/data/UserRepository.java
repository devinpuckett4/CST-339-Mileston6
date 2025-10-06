package com.gcu.workspacecst339.data;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.gcu.workspacecst339.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}