package com.dfernandezyopla.PianoReminder.Game.Services;

import com.dfernandezyopla.PianoReminder.Game.Entities.HistoryQuestion;

import java.util.List;

public interface QuestionService {
    List<HistoryQuestion> findAllHistoryQuestions();
}
