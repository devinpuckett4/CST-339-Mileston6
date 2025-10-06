package com.gcu.workspacecst339.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.gcu.workspacecst339.security.SecurityUserDetailsService;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final SecurityUserDetailsService uds;

    public SecurityConfig(SecurityUserDetailsService uds) {
        this.uds = uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(uds);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authenticationProvider(authProvider())

            // --- H2 console allowances ---
            .authorizeHttpRequests(auth -> auth
                // Permit the H2 console using Spring Boot's built-in matcher
                .requestMatchers(PathRequest.toH2Console()).permitAll()
                // Public endpoints
                .requestMatchers("/login", "/register", "/css/**", "/js/**", "/images/**").permitAll()
                // Everything else requires auth
                .anyRequest().authenticated()
            )
            // Ignore CSRF just for the H2 console
            .csrf(csrf -> csrf.ignoringRequestMatchers(PathRequest.toH2Console()))
            // Allow frames 
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))

            // Form login/logout
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/products", true)
                .failureUrl("/login?error")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }
}