package com.dfernandezyopla.PianoReminder.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(NoteNotFoundException.class)
    public ResponseEntity<CustomException> handleNoteNotFoundException(NoteNotFoundException ex) {
        CustomException error = new CustomException(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ChordNotFoundException.class)
    public ResponseEntity<CustomException> handleChordNotFoundException(ChordNotFoundException ex) {
        CustomException error = new CustomException(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HistoryQuestionNotFoundException.class)
    public ResponseEntity<CustomException> handleHistoryQuestionNotFoundException(HistoryQuestionNotFoundException ex) {
        CustomException error = new CustomException(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityConstrainedException.class)
    public ResponseEntity<CustomException> handleEntityConstrainedException(EntityConstrainedException ex) {
        CustomException error = new CustomException(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
