package com.example.notes.Repository;

import com.example.notes.Model.Notes.BaseNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BaseNoteRepo extends JpaRepository<BaseNote, Long> {
}
