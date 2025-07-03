package com.example.notes.DTOs.Note;

import com.example.notes.Model.Notes.ChecklistItem;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor

public final class ChecklistDTO extends BaseNoteDTO{

    @Nullable
    private List<ChecklistItem> checklistItemList = new ArrayList<>();
}
