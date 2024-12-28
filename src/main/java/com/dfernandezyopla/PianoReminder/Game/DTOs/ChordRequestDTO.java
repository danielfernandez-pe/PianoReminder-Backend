package com.dfernandezyopla.PianoReminder.Game.DTOs;

import com.dfernandezyopla.PianoReminder.Game.Entities.Clef;

import java.util.List;

public class ChordRequestDTO {
    private Clef clef;
    private String title;
    private List<Long> noteIds;

    public Clef getClef() {
        return clef;
    }

    public void setClef(Clef clef) {
        this.clef = clef;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Long> getNoteIds() {
        return noteIds;
    }

    public void setNoteIds(List<Long> noteIds) {
        this.noteIds = noteIds;
    }
}
