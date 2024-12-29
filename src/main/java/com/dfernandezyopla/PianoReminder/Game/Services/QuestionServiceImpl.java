package com.dfernandezyopla.PianoReminder.Game.Services;

import com.dfernandezyopla.PianoReminder.Exceptions.ChordNotFoundException;
import com.dfernandezyopla.PianoReminder.Exceptions.HistoryQuestionNotFoundException;
import com.dfernandezyopla.PianoReminder.Exceptions.NoteNotFoundException;
import com.dfernandezyopla.PianoReminder.Game.DTOs.ChordRequestDTO;
import com.dfernandezyopla.PianoReminder.Game.Entities.Chord;
import com.dfernandezyopla.PianoReminder.Game.Entities.HistoryQuestion;
import com.dfernandezyopla.PianoReminder.Game.Entities.Note;
import com.dfernandezyopla.PianoReminder.Game.Repositories.ChordRepository;
import com.dfernandezyopla.PianoReminder.Game.Repositories.HistoryQuestionRepository;
import com.dfernandezyopla.PianoReminder.Game.Repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final HistoryQuestionRepository historyQuestionRepository;
    private final NoteRepository noteRepository;
    private final ChordRepository chordRepository;

    @Autowired
    public QuestionServiceImpl(HistoryQuestionRepository historyQuestionRepository,
                               NoteRepository noteRepository,
                               ChordRepository chordRepository) {
        this.historyQuestionRepository = historyQuestionRepository;
        this.noteRepository = noteRepository;
        this.chordRepository = chordRepository;
    }

    @Override
    public List<HistoryQuestion> getHistoryQuestions() {
        return historyQuestionRepository.findAll();
    }

    @Override
    public List<Note> getNotes() {
        return noteRepository.findAll();
    }

    @Override
    public List<Chord> getChords() {
        return chordRepository.findAll();
    }

    @Override
    public HistoryQuestion getHistoryQuestionById(Long id) {
        return historyQuestionRepository.findById(id).orElseThrow(() -> new HistoryQuestionNotFoundException("History question not found"));
    }

    @Override
    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException("Note not found"));
    }

    @Override
    public Chord getChordById(Long id) {
        return chordRepository.findById(id).orElseThrow(() -> new ChordNotFoundException("Chord not found"));
    }

    @Override
    public Note createNote(Note note) {
        return noteRepository.save(note);
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
        return chordRepository.save(chord);
    }

    @Override
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public void deleteChord(Long id) {
        chordRepository.deleteById(id);
    }
}
