package com.dfernandezyopla.PianoReminder.Game.Services;

import com.dfernandezyopla.PianoReminder.Game.Entities.HistoryQuestion;
import com.dfernandezyopla.PianoReminder.Game.Entities.Note;
import com.dfernandezyopla.PianoReminder.Game.Repositories.HistoryQuestionRepository;
import com.dfernandezyopla.PianoReminder.Game.Repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final HistoryQuestionRepository historyQuestionRepository;
    private final NoteRepository noteRepository;

    @Autowired
    public QuestionServiceImpl(HistoryQuestionRepository historyQuestionRepository,
                               NoteRepository noteRepository) {
        this.historyQuestionRepository = historyQuestionRepository;
        this.noteRepository = noteRepository;
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
    public Note createNote(Note note) {
        return noteRepository.save(note);
    }
}
