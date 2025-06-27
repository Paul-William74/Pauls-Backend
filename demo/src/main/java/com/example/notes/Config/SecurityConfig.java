package com.example.notes.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Security configuration class for the application.
 * This class defines a bean for password encoding using BCrypt.
 */
@Configuration
public class SecurityConfig {

    /**
     * Bean for BCryptPasswordEncoder.
     * This encoder is used to hash passwords securely.
     *
     * @return a BCryptPasswordEncoder instance
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
