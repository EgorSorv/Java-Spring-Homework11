package ru.gb.Homework11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.Homework11.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
