package com.dfernandezyopla.PianoReminder.Game.Services;

import com.dfernandezyopla.PianoReminder.Game.Entities.HistoryQuestion;
import com.dfernandezyopla.PianoReminder.Game.Entities.Note;

import java.util.List;

public interface QuestionService {
    List<HistoryQuestion> getHistoryQuestions();
    List<Note> getNotes();
    Note createNote(Note note);
}
