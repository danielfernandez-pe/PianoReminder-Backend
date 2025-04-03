package com.dfernandezyopla.PianoReminder.Exceptions;

public class AuthFailException extends RuntimeException {
    public AuthFailException(String message) {
        super(message);
    }
}
