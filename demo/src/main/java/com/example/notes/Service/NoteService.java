package com.example.notes.Service;


import com.example.notes.DTOs.Note.ChecklistDTO;
import com.example.notes.DTOs.Note.RichTextDTO;
import com.example.notes.Mapper.NoteMapper;
import com.example.notes.Model.Notes.BaseNote;
import com.example.notes.Model.Notes.CheckListNote;
import com.example.notes.Model.Notes.NOTE_TYPE;
import com.example.notes.Model.Notes.RichTextNote;
import com.example.notes.Model.User.User;
import com.example.notes.Repository.BaseNoteRepo;
import com.example.notes.Repository.ChecklistNoteRepo;
import com.example.notes.Repository.RichTextRepo;
import com.example.notes.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private BaseNoteRepo noteRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RichTextRepo richTextRepo;

    @Autowired
    private ChecklistNoteRepo checklistNoteRepo;

    @Autowired
    private NoteMapper noteMapper;

    /**
     * Retrieves all notes for a specific user.
     *
     * @param userId user ID for which notes are to be retrieved
     * @return response entity containing the list of notes or an error message
     */
    public ResponseEntity<?> getUserNotes(Long userId) {
        //validate if the user exists
        Optional<User> userOptional = this.userRepo.findById(userId);
        if (userOptional.isEmpty()) //if user does not exist
            return ResponseEntity.badRequest().body("User not found");

        List<BaseNote> noteList = this.noteRepo.findAllByUserId(userId);

        //the return will change, according to the front end requirements(needs DTO)
        return ResponseEntity.ok(noteList);
    }

    public ResponseEntity<?> createRichTextNote(RichTextDTO richTextDTO, Long user_Id) {

        if (richTextDTO == null) {
            return ResponseEntity.badRequest().body("Invalid Note Type Object");
        }

        Optional<User> DbUser = userRepo.findById(user_Id);

        if (DbUser.isEmpty()) {
            return ResponseEntity.badRequest().body("User does not exist");
        }

        User user = DbUser.get();

        RichTextNote richTextNote = noteMapper.toRichTextNote(richTextDTO);
        richTextNote.setUser(user);
        richTextNote.setNoteType(NOTE_TYPE.RICH_TEXT);

        RichTextNote save = richTextRepo.save(richTextNote);
        RichTextDTO dto = noteMapper.toRichTextDto(save);


        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    public ResponseEntity<?> createChecklistNote(ChecklistDTO checklistDTO, Long user_Id) {

        if (checklistDTO == null) {
            return ResponseEntity.badRequest().body("Invalid Note Type Object");
        }

        Optional<User> DbUser = userRepo.findById(user_Id);

        if (DbUser.isEmpty()) {
            return ResponseEntity.badRequest().body("User does not exist");
        }

        User user = DbUser.get();

        CheckListNote checkListNote = noteMapper.toChecklistNote(checklistDTO);
        checkListNote.setUser(user);
        checkListNote.setNoteType(NOTE_TYPE.CHECK_LIST);

        CheckListNote save = checklistNoteRepo.save(checkListNote);

        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    //Still needs implementation
    public ResponseEntity<?> deleteAll(Long userId) {
        //Find specific user
        Optional<User> DbUser = userRepo.findById(userId);

        //Check if they exist in the database
        if (DbUser.isEmpty()) {
            return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
        }
        return null;
    }
}
