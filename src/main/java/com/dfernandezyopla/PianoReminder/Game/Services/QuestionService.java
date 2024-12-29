package com.dfernandezyopla.PianoReminder.Game.Services;

import com.dfernandezyopla.PianoReminder.Game.DTOs.ChordRequestDTO;
import com.dfernandezyopla.PianoReminder.Game.Entities.Chord;
import com.dfernandezyopla.PianoReminder.Game.Entities.HistoryQuestion;
import com.dfernandezyopla.PianoReminder.Game.Entities.Note;

import java.util.List;

public interface QuestionService {
    List<HistoryQuestion> getHistoryQuestions();
    List<Note> getNotes();
    List<Chord> getChords();
    HistoryQuestion getHistoryQuestionById(Long id);
    Note getNoteById(Long id);
    Chord getChordById(Long id);
    Note createNote(Note note);
    Chord createChord(ChordRequestDTO chord);
    void deleteNote(Long id);
    void deleteChord(Long id);
}
