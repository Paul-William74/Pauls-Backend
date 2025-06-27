package com.example.notes.Repository;

import com.example.notes.Model.Notes.BaseNote;
import com.example.notes.Model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseNoteRepo extends JpaRepository<BaseNote, Long> {

    //either of these methods can be used to find all notes by user
    List<BaseNote> findAllByUserId(User user);
    List<BaseNote> findAllByUserId(Long userId);
}
