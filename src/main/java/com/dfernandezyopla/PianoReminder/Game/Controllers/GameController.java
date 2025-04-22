package com.dfernandezyopla.PianoReminder.Game.Controllers;

import com.dfernandezyopla.PianoReminder.Game.DTOs.GameSessionRequestDTO;
import com.dfernandezyopla.PianoReminder.Game.DTOs.GameSessionResponseDTO;
import com.dfernandezyopla.PianoReminder.Game.Entities.GameSession;
import com.dfernandezyopla.PianoReminder.Game.Services.GameService;
import io.jsonwebtoken.Jwt;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    final private GameService gameService;

    @Autowired
    public GameController(GameService gameService) { this.gameService = gameService; }

    @GetMapping("/sessions")
    public List<GameSessionResponseDTO> getEntitiesToSync(HttpServletRequest httpRequest) {
        String authHeader = httpRequest.getHeader("Authorization");
        return gameService.getGameSessions(authHeader);
    }

    @PostMapping("/sessions")
    public GameSessionResponseDTO createGameSession(@RequestBody GameSessionRequestDTO gameSessionRequestDTO, HttpServletRequest httpRequest) {
        String authHeader = httpRequest.getHeader("Authorization");
        return gameService.createGameSession(gameSessionRequestDTO, authHeader);
    }
}
