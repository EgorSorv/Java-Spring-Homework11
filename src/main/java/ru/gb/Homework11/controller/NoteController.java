package ru.gb.Homework11.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.Homework11.model.Note;
import ru.gb.Homework11.service.NoteService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NoteController {
    private final NoteService service;

    // метрики
    private final Counter addNoteCounter = Metrics.counter("add_note_count");
    private final Counter deleteNoteCounter = Metrics.counter("delete_note_count");
    private final Counter updateNoteCounter = Metrics.counter("update_note_count");


    // создание заметки id
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        addNoteCounter.increment();
        return new ResponseEntity<>(service.createNote(note), HttpStatus.CREATED);
    }

    // вывод всех заметок id
    @GetMapping
    public ResponseEntity<List<Note>> findAll() {
        return new ResponseEntity<>(service.getAllNotes(), HttpStatus.OK);
    }

    // вывод заметки по id
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNote(@PathVariable("id") Long id) {
        Note findNote;

        try {
            findNote = service.getNoteById(id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Note());
        }

        return new ResponseEntity<>(findNote, HttpStatus.OK);
    }

    // обновление заметки по id
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable("id") Long id, @RequestBody Note note) {
        updateNoteCounter.increment();
        return new ResponseEntity<>(service.updateNoteById(id, note), HttpStatus.OK);
    }

    // удаление заметки по id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable("id") Long id) {
        service.deleteNoteById(id);
        deleteNoteCounter.increment();
        return ResponseEntity.ok().build();
    }
}
