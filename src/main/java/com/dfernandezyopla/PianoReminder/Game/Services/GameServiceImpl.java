package com.dfernandezyopla.PianoReminder.Game.Services;

import com.dfernandezyopla.PianoReminder.Auth.Entities.User;
import com.dfernandezyopla.PianoReminder.Auth.JwtUtils.JwtTokenUtil;
import com.dfernandezyopla.PianoReminder.Auth.Repositories.UserRepository;
import com.dfernandezyopla.PianoReminder.Game.DTOs.GameSessionRequestDTO;
import com.dfernandezyopla.PianoReminder.Game.DTOs.GameSessionResponseDTO;
import com.dfernandezyopla.PianoReminder.Game.Entities.GameSession;
import com.dfernandezyopla.PianoReminder.Game.Repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    final private GameRepository gameRepository;
    final private UserRepository userRepository;
    final private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, UserRepository userRepository, JwtTokenUtil jwtTokenUtil) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public List<GameSessionResponseDTO> getGameSessions(String authHeader) {
        String token = authHeader.substring(7); // Remove "Bearer "
        String email = jwtTokenUtil.getEmail(token);

        return gameRepository.findAllByUserEmail(email)
                .stream()
                .map(this::dtoFromGameSession)
                .toList();
    }

    @Override
    public GameSessionResponseDTO createGameSession(GameSessionRequestDTO request, String authHeader) {
        String token = authHeader.substring(7); // Remove "Bearer "
        String email = jwtTokenUtil.getEmail(token);

        System.out.println("For email " + email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        System.out.println("User found " + user);
        GameSession gameSession = new GameSession();
        gameSession.setId(request.getId());
        gameSession.setDate(request.getDate());
        gameSession.setPoints(request.getPoints());
        gameSession.setCorrectHistoryQuestions(request.getCorrectHistoryQuestions());
        gameSession.setCorrectSightReadingQuestions(request.getCorrectSightReadingQuestions());
        gameSession.setTotalHistoryQuestions(request.getTotalHistoryQuestions());
        gameSession.setTotalSightReadingQuestions(request.getTotalSightReadingQuestions());
        gameSession.setUser(user);
        return dtoFromGameSession(gameRepository.save(gameSession));
    }

    private GameSessionResponseDTO dtoFromGameSession(GameSession gamesession) {
        GameSessionResponseDTO response = new GameSessionResponseDTO();
        response.setId(gamesession.getId());
        response.setDate(gamesession.getDate());
        response.setPoints(gamesession.getPoints());
        response.setCorrectSightReadingQuestions(gamesession.getCorrectSightReadingQuestions());
        response.setCorrectHistoryQuestions(gamesession.getCorrectHistoryQuestions());
        response.setTotalHistoryQuestions(gamesession.getTotalHistoryQuestions());
        response.setTotalSightReadingQuestions(gamesession.getTotalSightReadingQuestions());
        return response;
    }
}
