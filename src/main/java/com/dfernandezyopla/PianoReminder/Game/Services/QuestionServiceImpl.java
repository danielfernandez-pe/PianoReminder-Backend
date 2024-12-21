package com.dfernandezyopla.PianoReminder.Game.Services;

import com.dfernandezyopla.PianoReminder.Game.Entities.HistoryQuestion;
import com.dfernandezyopla.PianoReminder.Game.Repositories.HistoryQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final HistoryQuestionRepository historyQuestionRepository;

    @Autowired
    public QuestionServiceImpl(HistoryQuestionRepository historyQuestionRepository) {
        this.historyQuestionRepository = historyQuestionRepository;
    }

    @Override
    public List<HistoryQuestion> findAllHistoryQuestions() {
        return historyQuestionRepository.findAll();
    }
}
