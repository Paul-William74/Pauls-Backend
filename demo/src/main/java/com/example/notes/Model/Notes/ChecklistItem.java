package com.example.notes.Model.Notes;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class ChecklistItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String point;

    @Column(nullable = false)
    private boolean isChecked = false;

    @ManyToOne(cascade = CascadeType.ALL)
    private CheckListNote checkListNote; //foreign key relationship to CheckListNote
}
