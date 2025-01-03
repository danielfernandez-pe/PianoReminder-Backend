package com.dfernandezyopla.PianoReminder.Game.Services;

import com.dfernandezyopla.PianoReminder.Exceptions.ChordNotFoundException;
import com.dfernandezyopla.PianoReminder.Exceptions.EntityConstrainedException;
import com.dfernandezyopla.PianoReminder.Exceptions.HistoryQuestionNotFoundException;
import com.dfernandezyopla.PianoReminder.Exceptions.NoteNotFoundException;
import com.dfernandezyopla.PianoReminder.Game.DTOs.ChordRequestDTO;
import com.dfernandezyopla.PianoReminder.Game.Entities.*;
import com.dfernandezyopla.PianoReminder.Game.Entities.Sync.EntityToSync;
import com.dfernandezyopla.PianoReminder.Game.Entities.Sync.EntityType;
import com.dfernandezyopla.PianoReminder.Game.Entities.Sync.SyncType;
import com.dfernandezyopla.PianoReminder.Game.Repositories.ChordRepository;
import com.dfernandezyopla.PianoReminder.Game.Repositories.EntityToSyncRepository;
import com.dfernandezyopla.PianoReminder.Game.Repositories.HistoryQuestionRepository;
import com.dfernandezyopla.PianoReminder.Game.Repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final HistoryQuestionRepository historyQuestionRepository;
    private final NoteRepository noteRepository;
    private final ChordRepository chordRepository;
    private final EntityToSyncRepository entityToSyncRepository;

    @Autowired
    public QuestionServiceImpl(HistoryQuestionRepository historyQuestionRepository,
                               NoteRepository noteRepository,
                               ChordRepository chordRepository,
                               EntityToSyncRepository entityToSyncRepository) {
        this.historyQuestionRepository = historyQuestionRepository;
        this.noteRepository = noteRepository;
        this.chordRepository = chordRepository;
        this.entityToSyncRepository = entityToSyncRepository;
    }

    @Override
    public List<HistoryQuestion> getHistoryQuestions() {
        return historyQuestionRepository.findAll();
    }

    @Override
    public HistoryQuestion getHistoryQuestionById(Long id) {
        return historyQuestionRepository.findById(id).orElseThrow(() -> new HistoryQuestionNotFoundException("History question not found"));
    }

    @Override
    public HistoryQuestion createHistoryQuestion(HistoryQuestion historyQuestion) {
        HistoryQuestion createdQuestion = historyQuestionRepository.save(historyQuestion);
        createEntityToSyncForCreation(createdQuestion.getId(), EntityType.HISTORY, SyncType.CREATED);
        return createdQuestion;
    }

    @Override
    public void deleteHistoryQuestion(Long id) {
        if (!historyQuestionRepository.existsById(id)) {
            throw new HistoryQuestionNotFoundException("Couldn't delete the question because Id doesn't exist");
        }

        try {
            historyQuestionRepository.deleteById(id);
            createEntityToSyncForCreation(id, EntityType.HISTORY, SyncType.DELETED);
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while deleting history question with ID " + id, e);
        }
    }

    @Override
    public List<Note> getNotes() {
        return noteRepository.findAll();
    }

    @Override
    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException("Note not found"));
    }

    @Override
    public Note createNote(Note note) {
        Note createdNote = noteRepository.save(note);
        createEntityToSyncForCreation(createdNote.getId(), EntityType.NOTE, SyncType.CREATED);
        return createdNote;
    }

    @Override
    public void deleteNote(Long id) {
        if (!noteRepository.existsById(id)) {
            throw new HistoryQuestionNotFoundException("Couldn't delete the note because Id doesn't exist");
        }

        try {
            noteRepository.deleteById(id);
            createEntityToSyncForCreation(id, EntityType.NOTE, SyncType.DELETED);
        } catch (DataIntegrityViolationException e) {
            throw new EntityConstrainedException("This note belongs to another entity: " + e);
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while deleting note with ID " + id, e);
        }
    }

    @Override
    public List<Chord> getChords() {
        return chordRepository.findAll();
    }

    @Override
    public Chord getChordById(Long id) {
        return chordRepository.findById(id).orElseThrow(() -> new ChordNotFoundException("Chord not found"));
    }

    @Override
    public Chord createChord(ChordRequestDTO chordDTO) {
        Set<Note> notes = new HashSet<>();

        for (Long noteId : chordDTO.getNoteIds()) {
            Note note = noteRepository.findById(noteId)
                    .orElseThrow(() -> new NoteNotFoundException(String.format("Note id %s not found", noteId)));
            notes.add(note);
        }

        Chord chord = new Chord();
        chord.setClef(chordDTO.getClef());
        chord.setTitle(chordDTO.getTitle());
        chord.setNotes(notes);

        Chord createdChord = chordRepository.save(chord);
        createEntityToSyncForCreation(createdChord.getId(), EntityType.CHORD, SyncType.CREATED);
        return createdChord;
    }

    @Override
    public void deleteChord(Long id) {
        if (!chordRepository.existsById(id)) {
            throw new ChordNotFoundException("Couldn't delete the chord because Id doesn't exist");
        }

        try {
            chordRepository.deleteById(id);
            createEntityToSyncForCreation(id, EntityType.CHORD, SyncType.DELETED);
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while deleting chord with ID " + id, e);
        }
    }

    @Override
    public List<EntityToSync> getEntitiesToSync() {
        return entityToSyncRepository.findAll();
    }

    private void createEntityToSyncForCreation(Long entityId, EntityType entityType, SyncType syncType) {
        EntityToSync newEntity = new EntityToSync();
        newEntity.setEntityId(entityId);
        newEntity.setEntityType(entityType);
        newEntity.setModifiedAt(Instant.now());
        newEntity.setSyncType(syncType);
        entityToSyncRepository.save(newEntity);
    }
}
