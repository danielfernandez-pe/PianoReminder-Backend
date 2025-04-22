package com.dfernandezyopla.PianoReminder.Game.Repositories;

import com.dfernandezyopla.PianoReminder.Game.Entities.GameSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<GameSession, String> {
    List<GameSession> findAllByUserEmail(String email);
}
