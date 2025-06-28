package com.example.notes.Controller;

import com.example.notes.Service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    public ResponseEntity<?> register() {
        return null;
    }

}