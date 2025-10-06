package com.gcu.workspacecst339.business;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gcu.workspacecst339.data.UserRepository;
import com.gcu.workspacecst339.model.User;

@Service
public class UserService {
    private final UserRepository users;
    private final PasswordEncoder encoder;

    public UserService(UserRepository users, PasswordEncoder encoder) {
        this.users = users;
        this.encoder = encoder;
    }

    public boolean usernameTaken(String username) {
        return users.existsByUsername(username);
    }

    public User register(String username, String rawPassword,
                         String firstName, String lastName, String email) {
        User u = new User(null, username, encoder.encode(rawPassword), firstName, lastName, email);
        u.setRole("ROLE_USER");
        return users.save(u);
    }

    public Optional<User> authenticate(String username, String rawPassword) {
        return users.findByUsername(username)
                    .filter(u -> encoder.matches(rawPassword, u.getPassword()));
    }
}


