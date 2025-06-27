package com.example.notes.Repository;

import com.example.notes.Model.Notes.CheckListNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChecklistNoteRepo extends JpaRepository<CheckListNote, Long> {
}
