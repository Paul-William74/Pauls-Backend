package com.example.notes.Repository;

import com.example.notes.Model.Notes.ChecklistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChecklistItemRepo extends JpaRepository<ChecklistItem, Long> {
}
