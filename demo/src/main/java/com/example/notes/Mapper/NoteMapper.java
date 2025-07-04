package com.example.notes.Mapper;

import com.example.notes.DTOs.Note.BaseNoteDTO;
import com.example.notes.DTOs.Note.ChecklistDTO;
import com.example.notes.DTOs.Note.ChecklistItemDTO;
import com.example.notes.DTOs.Note.RichTextDTO;
import com.example.notes.Model.Notes.BaseNote;
import com.example.notes.Model.Notes.CheckListNote;
import com.example.notes.Model.Notes.ChecklistItem;
import com.example.notes.Model.Notes.RichTextNote;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface NoteMapper {

    /**
     * Maps a RichTextDTO to a RichTextNote entity.
     */
    RichTextNote toRichTextNote(RichTextDTO dto);
    RichTextDTO toRichTextDTO(RichTextNote richTextNote);
    /**
     * Maps a ChecklistDTO to a ChecklistNote entity.
     */
    CheckListNote toChecklistNote(ChecklistDTO dto);
    ChecklistDTO toCheckListDTO(CheckListNote checkListNote);

    ChecklistItem toChecklistItem(ChecklistItemDTO dto);
    ChecklistItemDTO toChecklistItemDto(ChecklistItem checklistItem);

    default List<ChecklistItemDTO> toChecklistItemDTOs(List<ChecklistItem> items) {
        return items.stream()
                .map(this::toChecklistItemDto)
                .toList();
    }
    default BaseNoteDTO toBaseNoteDTO(BaseNote note) {
        if (note instanceof RichTextNote richNote) {
            return toRichTextDTO(richNote);
        } else if (note instanceof CheckListNote checkNote) {
            return toCheckListDTO(checkNote);
        } else {
            return null;
        }
    }

    default List<BaseNoteDTO> toBaseNoteDTOList(List<BaseNote> notes) {
        return notes.stream()
                .map(this::toBaseNoteDTO)
                .toList();
    }
}
