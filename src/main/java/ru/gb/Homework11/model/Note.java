package ru.gb.Homework11.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // название заметки
    @Column(name = "title", nullable = false)
    private String title;

    // описание заметки
    @Column(name = "description", nullable = false)
    private String description;

    // дата и время создания заметки
    @Column(name = "creation_time", nullable = false)
    private LocalDateTime creationTime;

    // присвоение текущего времени при создании заметки
    @PrePersist
    public void setCreationTime() {
        this.creationTime = LocalDateTime.now();
    }
}
