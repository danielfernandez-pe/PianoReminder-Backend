package com.dfernandezyopla.PianoReminder.Game.Entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Chord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Clef clef;

    private String title;

    @ManyToMany
    @JoinTable(
            name = "chord_note",
            joinColumns = @JoinColumn(name = "chord_id"),
            inverseJoinColumns = @JoinColumn(name = "note_id")
    )
    private Set<Note> notes;

    public Chord() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }
}
