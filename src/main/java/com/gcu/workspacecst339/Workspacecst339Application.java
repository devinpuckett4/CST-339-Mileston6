package com.gcu.workspacecst339;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gcu.workspacecst339.data.UserRepository;
import com.gcu.workspacecst339.model.User;

@SpringBootApplication
public class Workspacecst339Application {

    public static void main(String[] args) {
        SpringApplication.run(Workspacecst339Application.class, args);
    }

    // Seed an admin user
    @Bean
    CommandLineRunner seedAdmin(UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.findByUsername("admin").isEmpty()) {
                User u = new User(null, "admin", encoder.encode("admin123"),
                        "Admin", "User", "admin@example.com");
                u.setRole("ROLE_ADMIN");
                repo.save(u);
            }
        };
    }
}