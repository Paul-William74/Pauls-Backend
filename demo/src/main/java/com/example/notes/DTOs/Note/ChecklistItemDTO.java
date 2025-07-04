package com.example.notes.DTOs.Note;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChecklistItemDTO {

    @NotNull(message = "Note ID cannot be null")
    private Long id;

    @NotBlank(message = "Point cannot be null")
    private String point;

    @NotNull(message = "is checked boolean cannot be null")
    private boolean isChecked;
}
