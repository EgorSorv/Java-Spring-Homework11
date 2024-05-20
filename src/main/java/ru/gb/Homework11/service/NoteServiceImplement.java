package ru.gb.Homework11.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.Homework11.model.Note;
import ru.gb.Homework11.repository.NoteRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImplement implements NoteService {
    private final NoteRepository repository;

    // создание заметки
    @Override
    public Note createNote(Note note) {
        return repository.save(note);
    }

    // получение всех заметок
    @Override
    public List<Note> getAllNotes() {
        return repository.findAll();
    }

    // получение заметки по id
    @Override
    public Note getNoteById(Long id) {
        return repository.findById(id).orElseThrow(null);
    }

    // обновление заметки по id
    @Override
    public Note updateNoteById(Long id, Note note) {
        Note updatedNote = getNoteById(note.getId());
        updatedNote.setTitle(note.getTitle());
        updatedNote.setDescription(note.getDescription());
        updatedNote.setCreationTime(note.getCreationTime());

        return repository.save(updatedNote);
    }

    // удаление заметки по id
    @Override
    public void deleteNoteById(Long id) {
        repository.deleteById(id);
    }
}
