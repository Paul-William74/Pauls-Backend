package com.example.notes.Service;


import com.example.notes.Model.Notes.BaseNote;
import com.example.notes.Model.User.User;
import com.example.notes.Repository.BaseNoteRepo;
import com.example.notes.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
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


    /**
     * Retrieves all notes for a specific user.
     * @param userId user ID for which notes are to be retrieved
     * @return response entity containing the list of notes or an error message
     */
    public ResponseEntity<?> getUserNotes(Long userId) {
        //validate if the user exists
        Optional<User> userOptional = this.userRepo.findById(userId);
        if(userOptional.isEmpty()) //if user does not exist
            return ResponseEntity.badRequest().body("User not found");

        List<BaseNote> noteList = this.noteRepo.findAllByUserId(userId);

        //the return will change, according to the front end requirements(needs DTO)
        return ResponseEntity.ok(noteList);
    }

    /**
     * Registers a new user.
     * <p>
     * This method is currently a placeholder and needs to be implemented.
     * </p>
     *
     * @return a response entity with the registration result
     */
    public ResponseEntity<?> register() {
        return null; //TODO: needs to be implemented
    };
}
