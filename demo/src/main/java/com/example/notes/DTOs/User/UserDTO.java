package com.example.notes.DTOs.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserDTO {
    @NotNull(message = "Id cannot be null")
    private Long id;
    @NotBlank(message = "Username cannot be empty")
    private String username;
}
