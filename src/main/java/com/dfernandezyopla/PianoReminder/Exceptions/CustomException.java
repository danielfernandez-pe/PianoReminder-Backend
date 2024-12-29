package com.dfernandezyopla.PianoReminder.Exceptions;

public class CustomException {
    String message;

    public CustomException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
