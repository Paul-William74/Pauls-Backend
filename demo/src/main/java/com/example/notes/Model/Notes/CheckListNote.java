package com.example.notes.Model.Notes;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "check_list_note")
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class CheckListNote extends  BaseNote {

    @OneToMany(mappedBy = "checkListNote", orphanRemoval = true)
    private List<ChecklistItem> checklistItemList = new ArrayList<>();
}
