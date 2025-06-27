package com.example.notes.Mapper;

import com.example.notes.Model.Notes.RichTextNote;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoteMapper {


    RichTextNote toRichTextNote(String content);
}
