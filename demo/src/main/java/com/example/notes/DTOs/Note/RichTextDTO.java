package com.example.notes.DTOs.Note;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public final class RichTextDTO extends BaseNoteDTO {

    @NotBlank(message = "Rich text content cannot be null")
    private String richTextContent; // contains the content of the text
}
