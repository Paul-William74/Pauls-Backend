package com.example.notes.Model.Notes;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "rich_text_note")
@AllArgsConstructor
@NoArgsConstructor
public final class RichTextNote  extends BaseNote {
    private String richTextContent; // contains the content of the text
}
