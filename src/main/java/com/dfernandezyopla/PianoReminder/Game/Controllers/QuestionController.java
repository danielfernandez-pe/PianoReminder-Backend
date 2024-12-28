package com.dfernandezyopla.PianoReminder.Game.Controllers;

import com.dfernandezyopla.PianoReminder.Game.DTOs.ChordRequestDTO;
import com.dfernandezyopla.PianoReminder.Game.Entities.Chord;
import com.dfernandezyopla.PianoReminder.Game.Entities.HistoryQuestion;
import com.dfernandezyopla.PianoReminder.Game.Entities.Note;
import com.dfernandezyopla.PianoReminder.Game.Services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/chords")
    public List<Chord> getAllChords() {
        return questionService.getChords();
    }

    @PostMapping("/notes")
    public Note createNote(@RequestBody Note note) {
        return questionService.createNote(note);
    }

    @PostMapping("/chords")
    public Chord createChord(@RequestBody ChordRequestDTO chord) {
        return questionService.createChord(chord);
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable("id") Long id) {
        questionService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/chords/{id}")
    public ResponseEntity<Void> deleteChord(@PathVariable("id") Long id) {
        questionService.deleteChord(id);
        return ResponseEntity.noContent().build();
    }
}
