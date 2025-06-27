package com.example.notes.Repository;


import com.example.notes.Model.Notes.RichTextNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RichTextRepo extends JpaRepository<RichTextNote, Long> {
}
