package com.example.notes.DTOs.Note;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseNoteDTO {

    @NotNull(message = "Note ID cannot be null")
    private Long id;

    @NotBlank(message = "Title cannot be null")
    private String title; // Title of the note

    @Nullable
    private String description; // Description of the note
}
