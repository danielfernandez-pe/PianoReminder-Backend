package com.dfernandezyopla.PianoReminder.Game.Controllers;

import com.dfernandezyopla.PianoReminder.Game.Entities.HistoryQuestion;
import com.dfernandezyopla.PianoReminder.Game.Entities.Note;
import com.dfernandezyopla.PianoReminder.Game.Services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/history")
    public List<HistoryQuestion> getAllHistoryQuestions() {
        return questionService.getHistoryQuestions();
    }

    @GetMapping("/notes")
    public List<Note> getAllNotes() {
        return questionService.getNotes();
    }

    @PostMapping("/notes")
    public Note createNote(@RequestBody Note note) {
        return questionService.createNote(note);
    }
}
