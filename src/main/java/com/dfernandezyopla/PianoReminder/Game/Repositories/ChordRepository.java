package com.dfernandezyopla.PianoReminder.Game.Repositories;

import com.dfernandezyopla.PianoReminder.Game.Entities.Chord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChordRepository extends JpaRepository<Chord, Long> {
}
