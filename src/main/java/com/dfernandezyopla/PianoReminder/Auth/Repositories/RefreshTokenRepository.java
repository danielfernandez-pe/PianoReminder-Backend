package com.dfernandezyopla.PianoReminder.Auth.Repositories;

import com.dfernandezyopla.PianoReminder.Auth.Entities.RefreshToken;
import com.dfernandezyopla.PianoReminder.Auth.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findByUser(User user);
}