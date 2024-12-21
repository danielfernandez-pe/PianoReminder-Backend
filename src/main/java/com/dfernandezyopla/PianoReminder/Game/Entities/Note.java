package com.dfernandezyopla.PianoReminder.Game.Entities;

import jakarta.persistence.*;

enum Clef {
    TREBLE,
    BASS
}

enum Octave {
    OCT1,
    OCT2,
    OCT3,
    MIDDLEC,
    OCT5,
    OCT6,
    OCT7
}

enum NoteType {
    NATURAL,
    FLAT,
    SHARP
}

enum NoteValue {
    C,
    D,
    E,
    F,
    G,
    A,
    B
}

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NoteValue value;

    @Enumerated(EnumType.STRING)
    private Clef clef;

    @Enumerated(EnumType.STRING)
    private Octave octave;

    @Enumerated(EnumType.STRING)
    private NoteType type;

    public Note() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NoteValue getValue() {
        return value;
    }

    public void setValue(NoteValue value) {
        this.value = value;
    }

    public Clef getClef() {
        return clef;
    }

    public void setClef(Clef clef) {
        this.clef = clef;
    }

    public Octave getOctave() {
        return octave;
    }

    public void setOctave(Octave octave) {
        this.octave = octave;
    }

    public NoteType getType() {
        return type;
    }

    public void setType(NoteType type) {
        this.type = type;
    }
}