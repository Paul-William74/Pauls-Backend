package com.example.notes.Model.Notes;


import com.example.notes.Model.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "base_note")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BaseNote {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    protected String title;
    protected String description;

    @CreationTimestamp
    protected LocalDateTime createdAt;

    @LastModifiedDate
    protected LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    protected NOTE_TYPE noteType;


    @ManyToOne(cascade = CascadeType.ALL)
    protected User user; //many notes(of all types) can belong to one user
}
