package com.dfernandezyopla.PianoReminder.Game.Services;

import com.dfernandezyopla.PianoReminder.Game.DTOs.GameSessionRequestDTO;
import com.dfernandezyopla.PianoReminder.Game.DTOs.GameSessionResponseDTO;
import com.dfernandezyopla.PianoReminder.Game.Entities.GameSession;

import java.util.List;

public interface GameService {
    List<GameSessionResponseDTO> getGameSessions(String authHeader);
    GameSessionResponseDTO createGameSession(GameSessionRequestDTO gameSessionDTO, String authHeader);
}