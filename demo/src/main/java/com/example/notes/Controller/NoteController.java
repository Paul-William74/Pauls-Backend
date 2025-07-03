package com.example.notes.Controller;

import com.example.notes.DTOs.Note.ChecklistDTO;
import com.example.notes.DTOs.Note.RichTextDTO;
import com.example.notes.Service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



/**
 * Controller for handling note-related HTTP requests.
 */
@RestController
@RequestMapping("/notes")
public class NoteController {

    /**
     * Service for note operations.
     */
    @Autowired
    public NoteService noteService;

    @PostMapping("/create/RICH_TEXT/{userId}")
    public ResponseEntity<?> createRichText(
            @RequestBody RichTextDTO richTextDTO,
            @PathVariable Long userId) {
        return noteService.createRichTextNote(richTextDTO, userId);
    }

    @PostMapping("/create/CHECK_LIST/{userId}")
    public ResponseEntity<?> createChecklist(
            @RequestBody ChecklistDTO checklistDTO,
            @PathVariable Long userId) {
        return noteService.createChecklistNote(checklistDTO, userId);
    }

    @DeleteMapping("/deleteAllNotes/{userId}")
    public ResponseEntity<?> deleteAllUserNotes(@PathVariable Long userId) {
        //return noteService.deleteAll(userId);
        return null;
    }

}