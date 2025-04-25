package com.dfernandezyopla.PianoReminder.Auth.Services;

import com.dfernandezyopla.PianoReminder.Auth.Entities.RefreshToken;
import com.dfernandezyopla.PianoReminder.Auth.Entities.User;
import com.dfernandezyopla.PianoReminder.Auth.Repositories.RefreshTokenRepository;
import com.dfernandezyopla.PianoReminder.Exceptions.InvalidToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {
    private static final Duration REFRESH_TOKEN_VALIDITY = Duration.ofDays(2);

    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public String generateRefreshToken(User user) {
        String newToken = UUID.randomUUID().toString();
        RefreshToken refreshToken = refreshTokenRepository.findByUser(user).orElse(new RefreshToken());
        refreshToken.setToken(newToken);
        refreshToken.setExpireAt(Instant.now().plus(REFRESH_TOKEN_VALIDITY));
        refreshToken.setUser(user);
        refreshTokenRepository.save(refreshToken);
        return newToken;
    }

    public User validateRefreshTokenAndGetUser(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new InvalidToken("Invalid refresh token"));

        if (refreshToken.getExpireAt().isBefore(Instant.now())) {
            refreshTokenRepository.delete(refreshToken);
            throw new InvalidToken("Refresh token has expired");
        }

        return refreshToken.getUser();
    }
}
