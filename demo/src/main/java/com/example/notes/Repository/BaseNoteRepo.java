package com.example.notes.Repository;

import com.example.notes.Model.Notes.BaseNote;
import com.example.notes.Model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseNoteRepo extends JpaRepository<BaseNote, Long> {
    List<BaseNote> findAllByUserId(Long userId);
}
