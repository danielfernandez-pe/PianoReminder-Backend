package com.dfernandezyopla.PianoReminder.Game.Services;

import com.dfernandezyopla.PianoReminder.Game.DTOs.ChordRequestDTO;
import com.dfernandezyopla.PianoReminder.Game.Entities.Chord;
import com.dfernandezyopla.PianoReminder.Game.Entities.HistoryQuestion;
import com.dfernandezyopla.PianoReminder.Game.Entities.Note;
import com.dfernandezyopla.PianoReminder.Game.Entities.Sync.EntityToSync;

import java.time.Instant;
import java.util.List;

public interface QuestionService {
    List<HistoryQuestion> getHistoryQuestions();
    HistoryQuestion getHistoryQuestionById(Long id);
    HistoryQuestion createHistoryQuestion(HistoryQuestion historyQuestion);
    void deleteHistoryQuestion(Long id);

    List<Note> getNotes();
    Note getNoteById(Long id);
    Note createNote(Note note);
    void deleteNote(Long id);

    List<Chord> getChords();
    Chord getChordById(Long id);
    Chord createChord(ChordRequestDTO chord);
    void deleteChord(Long id);

    List<EntityToSync> getEntitiesToSync(Instant lastSynced);
}
