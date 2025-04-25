package com.dfernandezyopla.PianoReminder.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomException> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        CustomException error = new CustomException(errors.toString());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CustomException> handleMissingBodyException(HttpMessageNotReadableException ex) {
        CustomException error = new CustomException("Required request body is missing");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

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

    @ExceptionHandler(AuthFailException.class)
    public ResponseEntity<CustomException> handleAuthFailException(AuthFailException ex) {
        CustomException error = new CustomException(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidToken.class)
    public ResponseEntity<CustomException> handleInvalidToken(InvalidToken ex) {
        CustomException error = new CustomException(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
