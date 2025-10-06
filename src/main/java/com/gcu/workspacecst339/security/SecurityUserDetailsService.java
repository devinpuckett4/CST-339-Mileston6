package com.gcu.workspacecst339.security;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gcu.workspacecst339.data.UserRepository;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    private final UserRepository repo;

    public SecurityUserDetailsService(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var u = repo.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        var auths = List.of(new SimpleGrantedAuthority(
                u.getRole() == null ? "ROLE_USER" : u.getRole()
        ));

        return new org.springframework.security.core.userdetails.User(
                u.getUsername(),
                u.getPassword(),
                auths
        );
    }
}