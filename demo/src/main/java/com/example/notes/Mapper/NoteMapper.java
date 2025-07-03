package com.example.notes.Mapper;

import com.example.notes.DTOs.Note.ChecklistDTO;
import com.example.notes.DTOs.Note.RichTextDTO;
import com.example.notes.Model.Notes.CheckListNote;
import com.example.notes.Model.Notes.RichTextNote;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface NoteMapper {

    /**
     * Maps a RichTextDTO to a RichTextNote entity.
     */
    RichTextNote toRichTextNote(RichTextDTO dto);
    RichTextDTO toRichTextDto(RichTextNote richTextNote);
    /**
     * Maps a ChecklistDTO to a ChecklistNote entity.
     */
    CheckListNote toChecklistNote(ChecklistDTO dto);
    ChecklistDTO toCheckListDto(CheckListNote checkListNote);
}
