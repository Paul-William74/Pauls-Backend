package com.example.notes.DTOs.User;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a logged-in user in the application.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoggedInUser {

    @NotBlank(message = "User ID cannot be null")
    private Long id;

    @NotBlank(message = "Username cannot be null")
    private String username;
}
