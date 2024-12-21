package com.dfernandezyopla.PianoReminder.Game.Repositories;

import com.dfernandezyopla.PianoReminder.Game.Entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
