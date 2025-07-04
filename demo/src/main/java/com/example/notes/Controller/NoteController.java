package com.example.notes.Controller;

import com.example.notes.DTOs.Note.ChecklistDTO;
import com.example.notes.DTOs.Note.ChecklistItemDTO;
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

    @GetMapping("/findNotes/{userId}")
    public ResponseEntity<?> getNotes(@PathVariable Long userId){
        return noteService.getUserNotes(userId);
    }
    @PostMapping("/create/RICH_TEXT/{userId}")
    public ResponseEntity<?> createRichText(
            @RequestBody RichTextDTO richTextDTO,
            @PathVariable Long userId) {
        return noteService.createRichTextNote(richTextDTO, userId);
    }

    @PutMapping("/update/RICH_TEXT/{noteId}")
    public ResponseEntity<?> editRichText(
        @RequestBody RichTextDTO richTextDTO,
        @PathVariable Long noteId){
            return noteService.updateRichNote(richTextDTO,noteId);
    }

    @PostMapping("/create/CHECK_LIST/{userId}")
    public ResponseEntity<?> createChecklist(
            @RequestBody ChecklistDTO checklistDTO,
            @PathVariable Long userId) {
        return noteService.createChecklistNote(checklistDTO, userId);
    }

    @PutMapping("/update/CHECK_LIST/{noteId}")
    public ResponseEntity<?> editCheckListNote(
            @RequestBody ChecklistDTO checklistDTO,
            @PathVariable Long noteId){
        return noteService.updateChecklistNote(checklistDTO,noteId);
    }

    @PostMapping("/create/ITEM/{checklistNoteId}")
    public ResponseEntity<?> createCheckListItem(
            @RequestBody ChecklistItemDTO checklistItemDTO,
            @PathVariable Long checklistNoteId){
        return noteService.createChecklistItem(checklistItemDTO,checklistNoteId);
    }

    @PutMapping("/update/ITEM/{itemId}")
    public ResponseEntity<?> updateCheckListItem(
            @RequestBody ChecklistItemDTO checklistItemDTO,
            @PathVariable Long itemId){
        return noteService.updateChecklistItem(checklistItemDTO,itemId);
    }

    @DeleteMapping("/delete/{userId}/{noteId}")
    public ResponseEntity<?> deleteNote(@PathVariable Long userId, @PathVariable Long noteId) {
        return noteService.deleteNote(userId,noteId);
    }

}